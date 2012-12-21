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
