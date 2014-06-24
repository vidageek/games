package vggames.shared.tutor

import br.com.caelum.vraptor.Resource
import vggames.shared.Game
import br.com.caelum.vraptor.Result
import br.com.caelum.vraptor.Get
import vggames.shared.vraptor.VraptorExtensions._

@Resource
class TutorRoom(result : Result, game : Game) {

  @Get(Array("/tutor/${gameName}"))
  def findTutor(gameName : String) = {
    //    result.render(new RequestTutor)(game)
  }

}