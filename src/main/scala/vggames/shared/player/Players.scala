package vggames.shared.player

import org.scalaquery.ql.extended.ExtendedTable
import br.com.caelum.vraptor.ioc.Component
import org.scalaquery.session.Database
import org.scalaquery.ql._
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql.extended.SQLiteDriver.Implicit._
import org.scalaquery.session.Database.threadLocalSession

case class Player(name : String, email : String, token : String)

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

  def +=(player : Player) {
    Database.forURL("jdbc:sqlite:games.db", driver = "org.sqlite.JDBC").withSession {
      Players.insert(Player.unapply(player).get)
    }
  }

  def tuple2Player(t : (String, String, String)) = Player(t._1, t._2, t._3)
}

object Players extends ExtendedTable[(String, String, String)]("players") {

  def name = column[String]("name")
  def email = column[String]("email")
  def token = column[String]("token")

  def * = name ~ email ~ token
}