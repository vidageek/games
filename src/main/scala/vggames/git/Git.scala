package vggames.git

import scala.collection.JavaConverters._
import vggames.shared.task.Task
import java.util.{ List => JUList }

case class Git(parent : Git, commits : Map[String, List[Commit]], branch : String) {

  def ~(command : Command) = command(this)

  def getCommits : JUList[CommitList] = findCommits.asJava

  def findCommits : List[CommitList] = (List(br("stash"), br("work")) ++ nonSpecial ++
    List(br("master"), br("origin/master")) ++ nonSpecialRemotes).flatten

  def getBranch = branch

  def br(branch : String) = commits.get(branch).map(CommitList(branch, _))

  private def nonSpecial = commits.map(t => CommitList(t._1, t._2)).
    filterNot(e => Set("stash", "work", "master").contains(e.branch) || e.branch.contains("/")).
    map(Option(_))

  private def nonSpecialRemotes = commits.map(t => CommitList(t._1, t._2)).
    filter(e => e.branch.contains("/") && e.branch != "origin/master").
    map(Option(_))

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
  def apply() = new Git(null, Map("work" -> List()), "work")
}
