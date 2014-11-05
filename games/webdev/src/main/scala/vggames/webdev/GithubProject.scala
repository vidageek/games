package vggames.webdev

import vggames.shared.task.Task
import vggames.shared.task.TaskGroup
import vggames.shared.task.status.Ok
import vggames.shared.log.LogItem
import vggames.shared.Database
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession
import java.util.Date
import java.util.UUID
import java.io.File
import java.text.SimpleDateFormat
import org.eclipse.jgit.internal.storage.file.FileRepository
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.util.FileUtils
import vggames.shared.task.status.Failed
import vggames.shared.task.JudgedTask

class GithubProject(name: String, id: String)
  extends TaskGroup(name, id, new GithubTask()) {

}

class GithubTask() extends Task {

  def judge(challenge: String) =
    if (challenge.matches("^https://github.com/[^/]+/.+$")) Ok()
    else Failed("Url deve ter o padrão https://github.com/USUARIO/REPOSITORIO")

  def challenge = "Crie um projeto no github que resolva o problema apresentado ao lado e coloque a url abaixo"

  def resource = ""

  override def extraLog(playerId: Option[Long], challenge: String, gameName: String, judgedTask: JudgedTask) =
    if (judgedTask.ok)
      Some(GitRepo(playerId, s"$challenge.git", gameName))
    else None

}

case class GitRepo(playerId: Option[Long], challenge: String, gameName: String) extends LogItem with Database {

  def log = {
    val folder = "repos/" + playerId.map(folderName).getOrElse(UUID.randomUUID().toString)

    onDatabase {
      TableQuery[GitRepoSubmissionsTable] += (playerId, gameName, challenge, folder)
    }

    Git.cloneRepository().setURI(challenge).setDirectory(new File(folder)).call()
    FileUtils.delete(new File(folder + "/.git"), FileUtils.RECURSIVE)

  }

  private def folderName(i: Long) = {
    val today = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
    s"$today/$i-${new Date().getTime}"
  }

}

class GitRepoSubmissionsTable(tag: Tag) extends Table[(Option[Long], String, String, String)](tag, "gitRepoSubmissions") {

  def playerId = column[Option[Long]]("playerId")
  def gameName = column[String]("game")
  def repo = column[String]("repo")
  def folder = column[String]("folder")

  def * = (playerId, gameName, repo, folder)

}