package vggames.shared.player

import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConversions.asScalaConcurrentMap
import scala.collection.mutable.ConcurrentMap
import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.{ Cookie, HttpServletResponse }
import javax.servlet.http.HttpServletRequest
import org.scalaquery.ql.extended.ExtendedTable
import org.scalaquery.ql.extended.SQLiteDriver.Implicit._
import org.scalaquery.session.Database
import org.scalaquery.session.Database.threadLocalSession
import scala.collection.mutable.Map

@Component
class PlayerSession(request : HttpServletRequest, response : HttpServletResponse, players : Players) {

  def login(player : Player) : Unit = {
    response.addCookie(new LoginCookie(player.token, request.getServerName))
    PlayerSession.activePlayers += ((player.token, player))
  }

  def logout {
    response.addCookie(new LogoutCookie("player", request.getServerName))
  }

  def update(fns : Player => Player*) {
    actualPlayer.map { player =>
      players.update(fns.foldLeft(player)((p, fn) => fn(p)))
    }
  }

  def saveLast(lastTask : String) =
    update(p => { p.lastTask = Option(lastTask); p })

  def endGame = saveLast(null)

  def addActiveTime(activeTime : Long) =
    update(p => { p.activeTime += activeTime; p })

  def actualPlayer : Option[Player] = {
    if (request.getAttribute("player") != null) {
      return Some(request.getAttribute("player").asInstanceOf[Player])
    }
    val cookie = Option(request.getCookies).getOrElse(Array()).find(_.getName == "player")
    if (!cookie.isDefined) return None

    val token = cookie.get.getValue
    val player = PlayerSession.activePlayers.get(token)
    if (player.isDefined) return player

    players.find(token)
  }

  def finishGroup(group : String) = actualPlayer.map(players.finishGroup(_, group))

  def finishedGroups = actualPlayer.map { p =>
    players.finishedGroups(p).foldLeft(Map[String, String]()) { (map, group) =>
      map += group -> "finished"
    }
  }.getOrElse(Map[String, String]())

  def whenNotLogged(f : => Unit) : Unit = actualPlayer.getOrElse(f)

}

object PlayerSession {
  val activePlayers : ConcurrentMap[String, Player] = asScalaConcurrentMap(new ConcurrentHashMap[String, Player]())
}

class LogoutCookie(token : String, domain : String) extends Cookie("player", token) {
  setMaxAge(0)
  setPath("/")
  setDomain(domain)
}

class LoginCookie(token : String, domain : String) extends Cookie("player", token) {
  setMaxAge(5 * 365 * 24 * 60 * 60)
  setPath("/")
  setDomain(domain)
}

