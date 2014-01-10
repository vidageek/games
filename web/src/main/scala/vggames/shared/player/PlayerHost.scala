package vggames.shared.player

import java.security.SecureRandom
import br.com.caelum.vraptor.{ Get, Post, Resource, Result }
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.view.Results.referer
import vggames.shared.email.Mail
import vggames.shared.vraptor.Referer
import vggames.shared.GamesConfiguration
import vggames.shared.vraptor.GameFactoryCache
import scala.collection.JavaConverters._
import vggames.shared.view.TypedView
import vggames.shared.view.Home
import vggames.shared.vraptor.VraptorExtensions._
import vggames.shared.view.Senha

@Resource
class PlayerHost(players : Players, session : PlayerSession, result : Result,
                 referer : Referer, cfg : GamesConfiguration, games : GameFactoryCache) {

  @Get(Array("/"))
  def home = {
    result.render(new Home)(cfg, games)
  }

  @Get(Array("/senha"))
  def senha = {
    result.render(new Senha)()
  }

  @Get(Array("/about"))
  def about = {}

  @Get(Array("/token/{token}"))
  def login(token : String) = {
    players.find(token).map(session.login)
    result.redirectTo(this).home
  }

  @Post(Array("/player"))
  def authenticate(email : String) = {

    val player : Player = players.findByEmail(email).getOrElse(players += Player(Long.MaxValue, email, PlayerHost.secureToken, None))

    Mail(email, "games@vidageek.net", "Link para Login",
      s"""<a href="http://games.vidageek.net/token/${player.token}">Clique aqui para logar-se no VidaGeek Games</a>""").send

    result.include("notice", s"Enviamos um email para ${email} com o seu link de login. Basta clicar nele para se logar.")
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