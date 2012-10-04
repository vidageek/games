package vggames.shared.player

import org.scalaquery.ql.extended.ExtendedTable
import br.com.caelum.vraptor.ioc.Component
import org.scalaquery.session.Database
import org.scalaquery.ql._
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql.extended.SQLiteDriver.Implicit._
import org.scalaquery.session.Database.threadLocalSession

case class Player(email : String, token : String, var lastTask : Option[String]) {
  def getEmail : String = email
  def getLastTask : String = lastTask.getOrElse(null)
}

@Component
class Players {

  def find(token : String) : Option[Player] = {
    Database.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession {
      val query = for (player <- Players if player.token is token) yield player.*
      query.firstOption.map(tuple2Player)
    }
  }

  def findByEmail(email : String) : Option[Player] = {
    Database.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession {
      val query = for (player <- Players if player.email is email) yield player.*
      query.firstOption.map(tuple2Player)
    }
  }

  def +=(player : Player) : Player = {
    Database.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession {
      Players.insert(Player.unapply(player).get)
    }
    player
  }

  def updateLastTask(lastTask : String, playerOption : Option[Player]) {
    playerOption.map { p =>
      Database.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession {
        val query = for (player <- Players if player.email is p.email) yield player.*
        query.update(Player.unapply(p).get)
      }
    }
  }

  def tuple2Player(t : (String, String, Option[String])) = Player(t._1, t._2, t._3)
}

object Players extends ExtendedTable[(String, String, Option[String])]("players") {

  def email = column[String]("email")
  def token = column[String]("token")
  def lastTask = column[Option[String]]("lastTask")

  def * = email ~ token ~ lastTask
}