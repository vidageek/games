package vggames.shared.player

import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConversions.asScalaConcurrentMap
import scala.collection.mutable.ConcurrentMap
import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.{ Cookie, HttpServletResponse }
import javax.servlet.http.HttpServletRequest

@Component
class PlayerSession(request : HttpServletRequest, response : HttpServletResponse, players : Players) {

  def login(player : Player) : Unit = {
    response.addCookie(new LoginCookie(player.token))
    PlayerSession.activePlayers += ((player.token, player))
  }

  def actualPlayer : Option[Player] = {
    val cookie = request.getCookies.find(_.getName == "player")
    if (!cookie.isDefined) return None

    val token = cookie.get.getValue
    val player = PlayerSession.activePlayers.get(token)
    if (player.isDefined) return player

    players.find(token)
  }
}

object PlayerSession {
  val activePlayers : ConcurrentMap[String, Player] = asScalaConcurrentMap(new ConcurrentHashMap[String, Player]())
}

class LoginCookie(token : String) extends Cookie("player", token) {
  setMaxAge(Integer.MAX_VALUE)
}