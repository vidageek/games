package vggames.shared.player

import br.com.caelum.vraptor.{ Resource, Get }
import br.com.caelum.vraptor.Get
import br.com.caelum.vraptor.Resource

@Resource
class PlayerHost(players : Players, session : PlayerSession) {

  @Get(Array("/"))
  def home = {}

  @Get(Array("/token/{token}"))
  def authenticate(token : String) = players.find(token).map(session.login)
}