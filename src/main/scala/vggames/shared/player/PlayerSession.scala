package vggames.shared.player

import java.util.concurrent.ConcurrentHashMap

import scala.collection.JavaConversions.asScalaConcurrentMap
import scala.collection.mutable.ConcurrentMap

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.{Cookie, HttpServletResponse}

@Component
class PlayerSession(response : HttpServletResponse) {

  def login(player : Player) : Unit = {
    response.addCookie(new LoginCookie(player.token))
    PlayerSession.activePlayers += ((player.token, player))
  }
}

object PlayerSession {
  val activePlayers : ConcurrentMap[String, Player] = asScalaConcurrentMap(new ConcurrentHashMap[String, Player]())
}

class LoginCookie(token : String) extends Cookie("player", token) {
  setMaxAge(Integer.MAX_VALUE)
}