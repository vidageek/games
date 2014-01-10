package vggames.shared

import scala.io.Source
import br.com.caelum.vraptor.{ Get, Resource, Result, View }
import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletResponse

@Resource
class GameResource(result : Result) {

  @Get(Array("/play/{gameName}/resource/{resource}"))
  def findResource(gameName : String, resource : String) =
    result.use(classOf[ResourceView]).from(resource)
}

@Component
class ResourceView(game : Game, res : HttpServletResponse) extends View {

  def from(resource : String) = {
    game.resourceDescription.map { desc =>
      res.setCharacterEncoding("UTF-8")
      res.setContentType(desc.contentType)
      res.getWriter().println(
        Source.fromInputStream(getClass.getResourceAsStream(
          s"/${desc.gameName}/$resource.${desc.extension}")).mkString)
    }
  }
}