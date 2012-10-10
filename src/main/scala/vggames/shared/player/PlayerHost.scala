package vggames.shared.player

import java.security.SecureRandom
import scala.Array.canBuildFrom
import br.com.caelum.vraptor.{ Get, Post, Resource, Result }
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.view.Results.referer
import vggames.shared.email.Mail
import vggames.shared.vraptor.Referer

@Resource
class PlayerHost(players : Players, session : PlayerSession, result : Result, referer : Referer) {

  @Get(Array("/"))
  def home = {}

  @Get(Array("/senha"))
  def senha = {}

  @Get(Array("/token/{token}"))
  def login(token : String) = {
    players.find(token).map(session.login)
    result.redirectTo(this).home
  }

  @Post(Array("/player"))
  def authenticate(email : String) = {
    val outterEmail = email

    val player : Player = players.findByEmail(email).getOrElse(players += Player(Long.MaxValue, outterEmail, PlayerHost.secureToken, None))

    Mail(email, "games@vidageek.net", "Link para Login",
      """<a href="http://games.vidageek.net/token/%s">Clique aqui para logar-se no VidaGeek Games</a>""".
        format(player.token)).send

    result.include("notice", "Enviamos um email para %s com o seu link de login. Basta clicar nele para se logar.".format(email))
    result.redirectTo(referer.url)
  }

  @Get(Array("/logout"))
  def logout {
    session.logout
    result.redirectTo(referer.url)
  }
}

object PlayerHost {
  def secureToken : String = {
    val bytes = new Array[Byte](60)
    new SecureRandom().nextBytes(bytes)
    bytes.map("%02X" format _).mkString
  }
}