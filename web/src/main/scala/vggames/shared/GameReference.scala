package vggames.shared

import br.com.caelum.vraptor.Resource
import vggames.shared.vraptor.RequestData
import br.com.caelum.vraptor.Get
import br.com.caelum.vraptor.Result
import vggames.shared.player.PlayerSession
import br.com.caelum.vraptor.view.Results
import vggames.shared.vraptor.VraptorExtensions._
import vggames.shared.view.Reference

@Resource
class GameReference(data : RequestData, game : Game, result : Result, session : PlayerSession) {

  @Get(Array("/theory/{gameName}"))
  def theory(gameName : String) = {
    result.permanentlyRedirectTo(this).reference(gameName);
    result.use(Results.nothing)
  }

  @Get(Array("/reference/{gameName}"))
  def reference(gameName : String) = {
    result.render(new Reference)(gameName, game, session.finishedGroups)
  }

}