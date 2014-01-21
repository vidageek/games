package vggames.shared

import br.com.caelum.vraptor.{Get, Resource, Result}
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.view.Results
import vggames.shared.player.PlayerSession
import vggames.shared.vraptor.RequestData

@Resource
class GameReference(data : RequestData, game : Game, result : Result, session : PlayerSession) {

  @Get(Array("/theory/{gameName}"))
  def theory(gameName : String) = {
    result.permanentlyRedirectTo(s"/play/$gameName")
    result.use(Results.nothing)
  }

  @Get(Array("/reference/{gameName}"))
  def reference(gameName : String) = {
    result.permanentlyRedirectTo(s"/play/$gameName")
    result.use(Results.nothing)
  }

}