package vggames.html

import scala.io.Source

import br.com.caelum.vraptor.{ Get, Resource, Result, View }
import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletResponse

@Resource
class GameResource(result : Result) {

  @Get(Array("/play/html/resource/{resource}"))
  def findResource(resource : String) =
    result.use(classOf[Html]).from(resource)
}

@Component
class Html(res : HttpServletResponse) extends View {

  def from(resource : String) = {
    res.setContentType("text/html")
    res.getWriter().println(
      Source.fromInputStream(getClass.getResourceAsStream("/html/%s.html".format(resource))).mkString)
  }
}