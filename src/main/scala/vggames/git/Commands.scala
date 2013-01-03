package vggames.git

import scala.util.parsing.combinator.RegexParsers

trait Command {
  def apply(repo : Git, parent : Git) : Git
  def challenge : String
}

object Command extends RegexParsers {

  private def command : Parser[Command] = "git" ~> (init | commit | branch | checkout)

  private def init = "init" ~> id ^^ {
    case repo => Init(repo)
  }

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
      case a => None
    }
  }
}

case class Commit(name : String) extends Command {
  def apply(repo : Git, parent : Git) : Git = {
    val r = if (repo.branch == "") { repo ~< Checkout("master", true) } else repo
    val commits = r.commits.get(r.branch).getOrElse(List[Commit]()) :+ this
    Git(repo.repo, parent, this, r.commits + (r.branch -> commits), r.branch)
  }
  def challenge = "Fa&ccedil;a um commit com a mensagem <code>%s</code>".format(name)
  override def toString = name
}

case class Checkout(branch : String, bFlag : Boolean = false) extends Command {
  def apply(repo : Git, parent : Git) : Git = {
    if (repo.commits.keySet.contains(branch))
      return Git(repo.repo, parent, this, repo.commits, branch)
    if (bFlag) {
      val r = repo ~ Branch(branch) ~< Checkout(branch)
      Git(r.repo, r.parent, this, r.commits, r.branch)
    } else repo
  }
  def challenge = (if (bFlag) "Crie o branch <code>%s</code> e mude para ele"
  else "Mude para o branch <code>%s</code>").format(branch)
}

case class Branch(name : String) extends Command {
  def apply(repo : Git, parent : Git) = {
    val commits = repo.commits.get(repo.branch).getOrElse(List[Commit]())
    Git(repo.repo, parent, this, repo.commits + (name -> commits), repo.branch)
  }
  def challenge = "Crie o branch <code>%s</code>".format(name)
}

case class Init(repo : String) extends Command {

  def apply(repo : Git, parent : Git) =
    Git(this.repo, parent, this, repo.commits, repo.branch)

  def challenge = "Crie o reposit&oacute;rio <code>%s</code>".format(repo)

}