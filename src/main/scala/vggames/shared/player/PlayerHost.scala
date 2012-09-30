package vggames.shared.player

import br.com.caelum.vraptor.{ Get, Resource, Result }
import br.com.caelum.vraptor.ioc.Component
import vggames.shared.GameConsole
import java.security.SecureRandom
import vggames.shared.email.Mail

@Resource
class PlayerHost(players : Players, session : PlayerSession, result : Result) {

  @Get(Array("/"))
  def home = {}

  @Get(Array("/token/{token}"))
  def login(token : String) = {
    players.find(token).map(session.login)
    result.redirectTo(this).home
  }

  @Get(Array("/player"))
  def authenticate(name : String, email : String) = {
    val player : Player = players.findByEmail(email).getOrElse {
      val player = Player(name, email, secureToken)
      players += player
      player
    }
    Mail(email, "games@vidageek.net", "Link para Login", """Seu link para login &eacute; 
        <a href="http://games.vidageek.net/token/%s">http://games.vidageek.net/token/%s</a>""".format()).send
  }

  def secureToken : String = {
    val bytes = new Array[Byte](120)
    new SecureRandom().nextBytes(bytes)
    bytes.map("%02X" format _).mkString
  }
}