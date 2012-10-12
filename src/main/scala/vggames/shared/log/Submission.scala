package vggames.shared.log

import java.sql.Date
import org.scalaquery.ql.TypeMapper.{ BooleanTypeMapper, DateTypeMapper, StringTypeMapper }
import org.scalaquery.ql.extended.ExtendedTable
import org.scalaquery.ql.extended.SQLiteDriver.Implicit.columnBaseToInsertInvoker
import org.scalaquery.session.Database.threadLocalSession
import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.Database

case class Submission(gameName : String, task : Task, challenge : String, result : JudgedTask) extends LogItem with Database {
  def log {
    onDatabase {
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