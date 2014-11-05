package vggames.shared.player

import br.com.caelum.vraptor.ioc.Component
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession
import vggames.shared.Database
import scala.slick.lifted.TableQuery

case class Player(id: Long, email: String, token: String, var lastTask: Option[String], var activeTime: Long = 0) {
  def level: Long = activeTime / (60 * 15)
  def progress: Double = (activeTime % (60 * 15)).toDouble / (60 * 15) * 100
}

@Component
class Players extends Database {

  def find(token: String): Option[Player] = {
    onDatabase {
      val query = for (player <- TableQuery[PlayersTable] if player.token === token) yield player.*
      query.firstOption.map(tuple2Player)
    }
  }

  def findByEmail(email: String): Option[Player] = {
    onDatabase {
      val query = for (player <- TableQuery[PlayersTable] if player.email === email) yield player.*
      query.firstOption.map(tuple2Player)
    }
  }

  def +=(p: Player): Player = {
    onDatabase {
      TableQuery[PlayersTable].map(p => (p.email, p.token, p.lastTask, p.activeTime)) +=
        (p.email, p.token, p.lastTask, p.activeTime)
    }
    findByEmail(p.email).get
  }

  def update(p: Player) {
    onDatabase {
      val query = for (player <- TableQuery[PlayersTable] if player.email === p.email) yield player.*
      query.update(Player.unapply(p).get)
    }
  }

  def finishGroup(p: Player, group: String) {
    onDatabase {
      TableQuery[FinishedGroupsTable] += ((p.id, group))
    }
  }

  def finishedGroups(p: Player) = {
    onDatabase {
      val query = for (group <- TableQuery[FinishedGroupsTable] if group.playerId === p.id) yield group.group
      query.list
    }
  }

  def tuple2Player(t: (Long, String, String, Option[String], Long)) = Player(t._1, t._2, t._3, t._4, t._5)
}

class PlayersTable(tag: Tag) extends Table[(Long, String, String, Option[String], Long)](tag, "players") {

  def id = column[Long]("id")
  def email = column[String]("email")
  def token = column[String]("token")
  def lastTask = column[Option[String]]("lastTask")
  def activeTime = column[Long]("activeTime")

  def * = (id, email, token, lastTask, activeTime)
}

class FinishedGroupsTable(tag: Tag) extends Table[(Long, String)](tag, "finishedGroups") {

  def playerId = column[Long]("player_id")
  def group = column[String]("group")

  def * = (playerId, group)
}