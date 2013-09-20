package vggames.css

import br.com.caelum.vraptor.Result
import javax.servlet.http.HttpServletResponse
import br.com.caelum.vraptor.View
import br.com.caelum.vraptor.ioc.Component
import scala.io.Source
import br.com.caelum.vraptor.Resource
import br.com.caelum.vraptor.Get

@Resource
class GameResource(result : Result) {

  @Get(Array("/play/css/resource/{resource}"))
  def gameCss(resource : String) =
    result.use(classOf[Css]).from(resource)

}

@Component
class Css(res : HttpServletResponse) extends View {
  def from(resource : String) {
    res.setContentType("text/css")
    res.getWriter().println(
      Source.fromInputStream(getClass.getResourceAsStream("/css/${resource}.css")).mkString)
  }
}