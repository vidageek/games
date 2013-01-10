package vggames.git

import scala.util.parsing.combinator.RegexParsers

trait Command {
  def apply(repo : Git, parent : Git) : Git
  def challenge : String
}

object Command extends RegexParsers {

  private def command : Parser[Command] = "git" ~> (init | add | commit | branch | checkout | merge | rebase)

  private def init = "init" ~> id ^^ {
    case repo => Init(repo)
  }

  private def add = "add" ~> "\\S+".r ^^ {
    case path => Add(path)
  }

  private def commit = "commit" ~> (aFlagMessage | messageAFlag | message)

  private def message = "-m" ~ "\"" ~> "[^\"]*".r <~ "\"" ^^ {
    case message => Commit(message)
  }

  private def aFlagMessage = "-a" ~ "-m" ~ "\"" ~> "[^\"]*".r <~ "\"" ^^ {
    case message => Commit(message, true)
  }

  private def messageAFlag = "-m" ~ "\"" ~> "[^\"]*".r <~ "\"" ~ "-a" ^^ {
    case message => Commit(message, true)
  }

  private def branch = "branch" ~> (branchCreateDelete | branchMove)

  private def branchCreateDelete = opt("-D") ~ id ^^ {
    case None ~ branch => Branch(branch)
    case Some(_) ~ branch => DeleteBranch(branch)
  }

  private def branchMove = "-m" ~> id ~ id ^^ {
    case from ~ to => MoveBranch(from, to)
  }

  private def checkout = "checkout" ~> opt("-b") ~ id ^^ {
    case None ~ branch => Checkout(branch)
    case Some(_) ~ branch => Checkout(branch, true)
  }

  private def merge = "merge" ~> id ^^ {
    case branch => Merge(branch)
  }

  private def rebase = "rebase" ~> id ^^ {
    case branch => Rebase(branch)
  }

  private def id = "\\w+".r

  def apply(challenge : String) : Option[Command] = {
    parseAll(command, challenge) match {
      case Success(command, _) => Some(command)
      case a => None
    }
  }
}

case class Commit(name : String, aFlag : Boolean = false) extends Command {
  def apply(repo : Git, parent : Git) : Git = {
    val r = if (repo.branch == "") { repo ~ Checkout("master", true) } else repo
    val newCommits = r.commits.get(r.branch).getOrElse(List[Commit]()) :+ this
    val newFiles = if (aFlag) r.files + ("candidate" -> List()) + ("modified" -> List()) else r.files + ("candidate" -> List())
    r.copy(parent, this)(commits = r.commits + (r.branch -> newCommits), files = newFiles)
  }
  def challenge = if (aFlag)
    "Fa&ccedil;a um commit com a mensagem <code>%s</code> que tamb&eacute;m inclua os arquivos <strong>modified</strong>".format(name)
  else "Fa&ccedil;a um commit com a mensagem <code>%s</code>".format(name)

  override def toString = name
}

case class Checkout(branch : String, bFlag : Boolean = false) extends Command {
  def apply(repo : Git, parent : Git) : Git = {
    if (repo.commits.keySet.contains(branch))
      return repo.copy(parent, this)(branch = branch)
    if (bFlag) {
      val r = repo ~ Branch(branch) ~ Checkout(branch)
      r.copy(parent, this)(branch = r.branch)
    } else repo
  }
  def challenge = (if (bFlag) "Crie o branch <code>%s</code> e mude para ele"
  else "Mude para o branch <code>%s</code>").format(branch)
}

case class Branch(name : String) extends Command {
  def apply(repo : Git, parent : Git) = {
    val commits = repo.commits.get(repo.branch).getOrElse(List[Commit]())
    repo.copy(parent, this)(commits = repo.commits + (name -> commits))
  }
  def challenge = "Crie o branch <code>%s</code>".format(name)
}

case class DeleteBranch(name : String) extends Command {
  def apply(repo : Git, parent : Git) = {
    repo.copy(parent, this)(commits = repo.commits - name)
  }
  def challenge = "Apague o branch <code>%s</code>".format(name)
}

case class MoveBranch(from : String, to : String) extends Command {
  def apply(repo : Git, parent : Git) = {
    val commitList = repo.commits(from)
    repo.copy(parent, this)(commits = repo.commits - from + (to -> commitList))
  }
  def challenge = "Renomeie o branch <code>%s</code> para <code>%s</code>".format(from, to)
}

case class Init(repo : String) extends Command {

  def apply(repo : Git, parent : Git) = repo.copy(parent, this)(repo = this.repo)

  def challenge = "Crie o reposit&oacute;rio <code>%s</code>".format(repo)
}

case class Merge(branch : String) extends Command {

  def apply(repo : Git, parent : Git) = {
    val branchCommits = repo.commits(repo.branch)
    val otherCommits = repo.commits(branch)
    val base = branchCommits.zip(otherCommits).filter { case (a, b) => a.name == b.name }.map(_._1)
    val commits = branchCommits ++ otherCommits.slice(base.size, otherCommits.size)
    val newCommits = if (branchCommits.size == base.size) commits else commits :+ Commit("Merge branch %s".format(branch))

    repo.copy(parent, this)(commits = repo.commits + (repo.branch -> newCommits))
  }

  def challenge = "Faça o merge dos commits do branch <code>%s</code> no branch atual.".format(branch)
}

case class Rebase(branch : String) extends Command {

  def apply(repo : Git, parent : Git) = {
    val branchCommits = repo.commits(repo.branch)
    val otherCommits = repo.commits(branch)
    val base = branchCommits.zip(otherCommits).filter { case (a, b) => a.name == b.name }.map(_._1)

    val commits = otherCommits ++ branchCommits.slice(base.size, branchCommits.size)

    repo.copy(parent, this)(commits = repo.commits + (repo.branch -> commits))
  }

  def challenge = "Faça o rebase dos commits do branch <code>%s</code> no branch atual.".format(branch)
}

abstract class AddFile(path : String, kind : String) extends Command {
  def apply(repo : Git, parent : Git) = {
    val files : List[GitFile] = (repo.files(kind) :+ GitFile(path)).sortWith(_.path < _.path)
    repo.copy(parent, this)(files = repo.files + (kind -> files))
  }
  def challenge = throw new RuntimeException("este comando não gera desafios")
}

case class UntrackedFile(path : String) extends AddFile(path, "untracked")
case class ModifiedFile(path : String) extends AddFile(path, "modified")
case class CandidateFile(path : String) extends AddFile(path, "candidate")

case class Add(path : String, folder : Boolean = false) extends Command {
  def apply(repo : Git, parent : Git) = {
    val startPath = if (path == ".") "" else path
    val untracked = repo.files("untracked").filterNot(_.path.startsWith(startPath))
    val modified = repo.files("modified").filterNot(_.path.startsWith(startPath))
    val candidate = repo.files("candidate") ++ repo.files("untracked").filter(_.path.startsWith(startPath)) ++ repo.files("modified").filter(_.path.startsWith(startPath))
    repo.copy(parent, this)(files = Map("untracked" -> untracked, "modified" -> modified, "candidate" -> candidate.sortWith(_.path < _.path)))
  }

  def challenge : String = {
    if (path == ".") return "Adicione todos os arquivos da pasta atual &agrave; lista de commit candidate"
    if (folder) return "Adicione todos os arquivos da pasta <code>%s</code> &agrave; lista de commit candidate".format(path)
    "Adicione o arquivo <code>%s</code> &agrave; lista de commit candidate".format(path)
  }
}