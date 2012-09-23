package vggames.shared.log

import java.sql.Date

import org.scalaquery.ql.extended.ExtendedTable
import org.scalaquery.ql.extended.SQLiteDriver.Implicit._
import org.scalaquery.session.Database
import org.scalaquery.session.Database.threadLocalSession

import vggames.shared.task.{ JudgedTask, Task }

case class Submission(gameName : String, task : Task, challenge : String, result : JudgedTask) extends LogItem {
  def log {
    import org.scalaquery.ql._
    import org.scalaquery.ql.TypeMapper._
    import org.scalaquery.ql.extended.SQLiteDriver.Implicit._
    import org.scalaquery.session.Database.threadLocalSession
    Database.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession {
      Submissions.insert(gameName, task.getChallenge, challenge, result.getOk, new Date(new java.util.Date().getTime))
    }
  }
}

object Submissions extends ExtendedTable[(String, String, String, Boolean, Date)]("submissions") {

  def game = column[String]("game")
  def task = column[String]("task")
  def challenge = column[String]("challenge")
  def passed = column[Boolean]("passed")
  def date = column[Date]("date")

  def * = game ~ task ~ challenge ~ passed ~ date
}