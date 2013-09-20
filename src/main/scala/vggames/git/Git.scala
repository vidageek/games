package vggames.git

import scala.collection.JavaConverters._
import java.util.{ List => JUList }
import scala.collection.mutable.ListBuffer

case class Git(shouldBeTask : Boolean, repo : String, parent : Git, command : Command, commits : Map[String, List[Commit]],
  branch : String, files : Map[String, List[GitFile]]) {

  def ~(command : Command) = followedBy(command)

  def ~<(command : Command) = followedByWithoutTask(command)

  def followedBy(command : Command) = command(this, true)
  def followedByWithoutTask(command : Command) = command(this, false)

  val getCommits : JUList[CommitList] = findCommits.asJava
  val getFiles = files.map { case (k, v) => k -> v.asJava }.asJava

  def copy(parent : Git, command : Command, shouldBeTask : Boolean)(repo : String = this.repo, commits : Map[String, List[Commit]] = this.commits, branch : String = this.branch, files : Map[String, List[GitFile]] = this.files) =
    Git(shouldBeTask, repo, parent, command, commits, branch, files)

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

  lazy val tasks : List[GitTask] =
    allGits(this).reverse.foldLeft(List[GitTask]()) { (taskList, git) =>
      if (git.shouldBeTask) taskList :+ GitTask(git.parent, git)
      else taskList
    }

  private def allGits(repo : Git) : List[Git] =
    if (repo == null) List() else repo :: allGits(repo.parent)

  def diff(expected : Git) : List[String] = {
    val buffer = ListBuffer[String]()
    if (repo != expected.repo)
      buffer += s"Deveria ter criado o reposit&oacute;rio <code>${expected.repo}</code>. Foi criado o <code>${repo}</code>"

    if (branch != expected.branch)
      buffer += s"Deveria mudar para o branch <code>${expected.branch}</code>. Est&aacute; em <code>${branch}</code>"

    expected.commits.keySet.diff(commits.keySet).foreach { branch =>
      buffer += s"Deveria criar o branch <code>${branch}</code>."
    }

    commits.keySet.diff(expected.commits.keySet).foreach { branch =>
      buffer += s"N&atilde;o deveria criar o branch <code>${branch}</code>."
    }

    expected.commits.keySet.intersect(commits.keySet).foreach { branch =>
      expected.commits(branch).zip(commits(branch)).foldLeft(0) { (i, commit) =>
        if (commit._1 != commit._2)
          buffer += s"Commit <code>${i}</code> do branch <code>${branch}</code> deveria ser <code>${commit._1}</code>, mas foi <code>${commit._2}</code>"
        i + 1
      }
    }
    val reversedFiles = reverse(files)
    val allFiles = files.flatMap { case (kind, file) => file }.toSet
    reverse(expected.files).diff(reversedFiles).foreach {
      case (file, kind) => {
        if (allFiles.contains(file)) {
          buffer += s"Arquivo <code>${file}</code> deveria estar marcado como ${kind}."
        } else {
          buffer += s"Arquivo <code>${file}</code> deveria exister como ${kind}."
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
  def apply() = new Git(false, "", null, null, Map(), "", Map("untracked" -> List(), "modified" -> List(), "candidate" -> List()))
}

object WorkGit {
  def apply() = new Git(false, "repositorio", null, null, Map("work" -> List(), "master" -> List()), "work",
    Map("untracked" -> List(), "modified" -> List(), "candidate" -> List()))
}

object MasterGit {
  def apply() = new Git(false, "repositorio", null, null, Map("origin/master" -> List(), "master" -> List()), "master",
    Map("untracked" -> List(), "modified" -> List(), "candidate" -> List()))
}
