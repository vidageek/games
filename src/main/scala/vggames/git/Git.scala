package vggames.git

import scala.collection.JavaConverters._
import vggames.shared.task.Task
import java.util.{ List => JUList }
import scala.collection.mutable.ListBuffer

case class Git(repo : String, parent : Git, command : Command, commits : Map[String, List[Commit]], branch : String, files : Map[String, List[GitFile]]) {

  def ~(command : Command) = followedBy(command)

  def ~<(command : Command) = followedByWithoutTask(command)

  def followedBy(command : Command) = command(this, this)
  def followedByWithoutTask(command : Command) = command(this, parent)

  val getCommits : JUList[CommitList] = findCommits.asJava
  val getFiles = files.map { case (k, v) => k -> v.asJava }.asJava

  def copy(parent : Git, command : Command)(repo : String = this.repo, commits : Map[String, List[Commit]] = this.commits, branch : String = this.branch, files : Map[String, List[GitFile]] = this.files) =
    Git(repo, parent, command, commits, branch, files)

  def findCommits : List[CommitList] = (List(br("stash"), br("work")) ++ nonSpecial ++
    List(br("master"), br("origin/master")) ++ nonSpecialRemotes).flatten

  def getBranch = branch
  def getRepo = repo

  def br(branch : String) = commits.get(branch).map(CommitList(branch, _))

  private def nonSpecial = commits.map(t => CommitList(t._1, t._2)).
    filterNot(e => Set("stash", "work", "master").contains(e.branch) || e.branch.contains("/")).
    map(Option(_))

  private def nonSpecialRemotes = commits.map(t => CommitList(t._1, t._2)).
    filter(e => e.branch.contains("/") && e.branch != "origin/master").
    map(Option(_))

  val tasks : List[GitTask] = {
    val gits = allGits(this).reverse
    gits.zip(gits.tail).map(t => GitTask(t._1, t._2))
  }

  private def allGits(repo : Git) : List[Git] = {
    if (repo == null) List()
    else repo :: allGits(repo.parent)
  }

  def diff(expected : Git) : List[String] = {
    val buffer = ListBuffer[String]()
    if (repo != expected.repo)
      buffer += "Deveria ter criado o reposit&oacute;rio <code>%s</code>. Foi criado o <code>%s</code>".format(expected.repo, repo)

    if (branch != expected.branch)
      buffer += "Deveria mudar para o branch <code>%s</code>. Est&aacute; em <code>%s</code>".format(expected.branch, branch)

    expected.commits.keySet.diff(commits.keySet).foreach { branch =>
      buffer += "Deveria criar o branch <code>%s</code>.".format(branch)
    }

    commits.keySet.diff(expected.commits.keySet).foreach { branch =>
      buffer += "N&atilde;o deveria criar o branch <code>%s</code>.".format(branch)
    }

    expected.commits.keySet.intersect(commits.keySet).foreach { branch =>
      expected.commits(branch).zip(commits(branch)).foldLeft(0) { (i, commit) =>
        if (commit._1 != commit._2)
          buffer += "Commit <code>%s</code> do branch <code>%s</code> deveria ser <code>%s</code>, mas foi <code>%s</code>".
            format(i, branch, commit._1, commit._2)
        i + 1
      }
    }
    val reversedFiles = reverse(files)
    val allFiles = files.flatMap { case (kind, file) => file }.toSet
    reverse(expected.files).diff(reversedFiles).foreach {
      case (file, kind) => {
        if (allFiles.contains(file)) {
          buffer += "Arquivo <code>%s</code> deveria estar marcado como %s.".format(file, kind)
        } else {
          buffer += "Arquivo <code>%s</code> deveria exister como %s.".format(file, kind)
        }
      }
    }

    buffer.toList
  }

  private def reverse(map : Map[String, List[GitFile]]) = map.flatMap { case (k, v) => v.map(_ -> k) }.toSet

}

case class GitFile(path : String) {
  override def toString = path
}

case class CommitList(branch : String, commits : List[Commit]) {
  def getBranch = branch
  val getCommits = commits.asJava
}

object EmptyGit {
  def apply() = new Git("", null, null, Map(), "", Map("untracked" -> List(), "modified" -> List(), "candidate" -> List()))
}

object WorkGit {
  def apply() = new Git("repositorio", null, null, Map("work" -> List(), "master" -> List()), "work",
    Map("untracked" -> List(), "modified" -> List(), "candidate" -> List()))
}
