package vggames.shared.log

import java.sql.Date
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession
import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.Database
import vggames.shared.player.Player
import vggames.shared.task.Exercise
import scala.slick.lifted.Tag

case class Submission(gameName: String, task: Exercise, challenge: String, result: JudgedTask, player: Option[Player], ip: Option[String]) extends LogItem with Database {
  def log {
    onDatabase {
      TableQuery[SubmissionsTable].insert(gameName, task.challenge, challenge, result.ok, player.map(_.id), ip)
    }
  }
}

class SubmissionsTable(tag: Tag) extends Table[(String, String, String, Boolean, Option[Long], Option[String])](tag, "submissions") {

  def game = column[String]("game")
  def task = column[String]("task")
  def challenge = column[String]("challenge")
  def passed = column[Boolean]("passed")
  def playerId = column[Option[Long]]("playerId")
  def playerIp = column[Option[String]]("playerIp")

  def * = (game, task, challenge, passed, playerId, playerIp)
}