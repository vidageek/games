package vggames.git

import scala.collection.JavaConverters._
import vggames.shared.task.Task
import java.util.{ List => JUList }

case class Git(parent : Git, commits : Map[String, List[Commit]], branch : String) {

  def ~(command : Command) = command(this)

  def getCommits : JUList[CommitList] =
    (List(br("stash"), br("work")) ++ nonSpecial ++ List(br("master"), br("origin/master")) ++ nonSpecialRemotes).
      filterNot(_.commits.size == 0).asJava

  def br(branch : String) = CommitList(branch, commits.get(branch).getOrElse(List()))

  private def nonSpecial = commits.map(t => CommitList(t._1, t._2)).
    filterNot(e => Set("stash", "work", "master").contains(e.branch) || e.branch.contains("/"))

  private def nonSpecialRemotes = commits.map(t => CommitList(t._1, t._2)).
    filter(e => e.branch.contains("/") && e.branch != "origin/master")

  def tasks : List[GitTask] = {
    val gits = allGits(this).reverse
    gits.zip(gits.tail).map(t => GitTask(t._1, t._2))
  }

  private def allGits(repo : Git) : List[Git] = {
    if (repo == null) List()
    else repo :: allGits(repo.parent)
  }

}

case class CommitList(branch : String, commits : List[Commit]) {
  def getBranch = branch
  def getCommits = commits.asJava
}

object EmptyGit {
  def apply() = new Git(null, Map(), "work")
}

trait Command {
  def apply(repo : Git) : Git
}

case class Commit(name : String) extends Command {
  def apply(repo : Git) : Git = {
    if (Set("master", "stash", "origin/master").contains(repo.branch)) return repo
    val commits = repo.commits.get(repo.branch).getOrElse(List[Commit]()) :+ this
    new Git(repo, repo.commits + (repo.branch -> commits), repo.branch)
  }
  override def toString = name
}

case class Checkout(branch : String, bFlag : Boolean = false) extends Command {
  def apply(repo : Git) = if (repo.commits.keySet.contains(branch) || bFlag) new Git(repo, repo.commits, branch) else repo
}

case class Branch(name : String) extends Command {
  def apply(repo : Git) = {
    val commits = repo.commits.get(repo.branch).getOrElse(List[Commit]())
    new Git(repo, repo.commits + (name -> commits), repo.branch)
  }
}