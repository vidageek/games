package vggames.shared.log

import vggames.shared.player.Player
import org.scalaquery.ql.extended.ExtendedTable
import org.scalaquery.ql.extended.SQLiteDriver.Implicit._
import org.scalaquery.session.Database
import org.scalaquery.session.Database.threadLocalSession

case class EndGroup(groupName : String, player : Option[Player]) extends LogItem {

  override def log = {
    player.map { p =>
      Database.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession {
        FinishedGroups.insert((p.id, groupName))
      }
    }
  }

}

object FinishedGroups extends ExtendedTable[(Long, String)]("finishedGroups") {

  def playerId = column[Long]("player_id")
  def group = column[String]("group")

  def * = playerId ~ group
}