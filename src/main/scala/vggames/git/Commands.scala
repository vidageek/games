package vggames.git

import scala.util.parsing.combinator.RegexParsers

trait Command {
  def apply(repo : Git) : Git
}

object Command extends RegexParsers {

  private def command : Parser[Command] = "git" ~> (commit | branch | checkout)

  private def commit = "commit" ~ "-m" ~ "\"" ~> "[^\"]*".r <~ "\"" ^^ {
    case message => Commit(message)
  }

  private def branch = "branch" ~> id ^^ {
    case branch => Branch(branch)
  }

  private def checkout = "checkout" ~> opt("-b") ~ id ^^ {
    case None ~ branch => Checkout(branch)
    case Some(_) ~ branch => Checkout(branch, true)
  }

  private def id = "\\w+".r

  def apply(challenge : String) : Option[Command] = {
    parseAll(command, challenge) match {
      case Success(command, _) => Some(command)
      case a => { println(a); None }
    }
  }
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