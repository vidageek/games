package vggames.shared

import br.com.caelum.vraptor.{Get, Resource, Result}
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.view.Results
import vggames.shared.player.PlayerSession
import vggames.shared.vraptor.RequestData

@Resource
class GameLegacyUrls(data : RequestData, game : Game, result : Result, session : PlayerSession) {

  @Get(Array("/play/{gameName}/task/{i}"))
  def play(gameName : String, i : Int) = {
    result.permanentlyRedirectTo(s"/aprenda/$gameName/task/$i")
    result.use(Results.nothing)
  }

  @Get(Array("/play/{gameName}"))
  def play(gameName : String) = {
    result.permanentlyRedirectTo(s"/aprenda/$gameName")
    result.use(Results.nothing)
  }

  @Get(Array("/play/{gameName}/resource/{resource}"))
  def findGameResource(gameName: String, resource: String) = {
    result.permanentlyRedirectTo(s"/aprenda/$gameName/resource/$resource")
    result.use(Results.nothing)
  }


  @Get(Array("/theory/{gameName}"))
  def theory(gameName : String) = {
    result.permanentlyRedirectTo(s"/aprenda/$gameName")
    result.use(Results.nothing)
  }

  @Get(Array("/reference/{gameName}"))
  def reference(gameName : String) = {
    result.permanentlyRedirectTo(s"/aprenda/$gameName")
    result.use(Results.nothing)
  }

}
