package vggames.shared

import scala.io.Source
import br.com.caelum.vraptor.{ Get, Resource, Result, View }
import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletResponse
import br.com.caelum.vraptor.ioc.Component
import java.text.SimpleDateFormat
import java.util.Date
import org.apache.commons.io.IOUtils

@Resource
class GameResource(result: Result, cached: ResourceCache) {

  @Get(Array("/static/{gameName}/{resource}"))
  def findStaticResource(gameName: String, resource: String) =
    result.use(classOf[StaticResourceView]).from(resource)

  @Get(Array("/aprenda/{gameName}/resource/{resource}"))
  def findGameResource(gameName: String, resource: String) =
    result.use(classOf[GameResourceView]).from(resource)

  @Get(Array("/css/{gameName}.css"))
  def findCss(gameName: String) = {
    result.use(classOf[AssetView]).css(cached css gameName)
  }

  @Get(Array("/js/{gameName}.js"))
  def findJs(gameName: String) = {
    result.use(classOf[AssetView]).js(cached js gameName)
  }
}

@Component
class AssetView(res: HttpServletResponse) extends View {
  def css(resource: Array[Byte]) = {
    write(resource, "text/css")
  }

  def js(resource: Array[Byte]) = {
    write(resource, "application/x-javascript")
  }

  private def write(resource: Array[Byte], contentType: String) = {
    res.setContentType(contentType)
    res.addHeader("Content-Encoding", "gzip")
    res.addHeader("Expires", Expires date)
    res.getOutputStream().write(resource)
  }
}

@Component
class GameResourceView(game: Game, res: HttpServletResponse) extends View {

  def from(resource: String) = {
    game.resourceDescription.map { desc =>
      res.addHeader("Expires", Expires date)
      res.setCharacterEncoding("UTF-8")
      res.setContentType(desc.contentType)
      res.getWriter().println(
        Source.fromInputStream(getClass.getResourceAsStream(
          s"/${desc.gameName}/$resource.${desc.extension}")).mkString)
    }
  }
}

@Component
class StaticResourceView(game: Game, res: HttpServletResponse) extends View {

  def from(resource: String) = {
    res.addHeader("Expires", Expires date)
    res.setContentType(ContentTypeOf(resource))
    IOUtils.copy(getClass.getResourceAsStream(
      s"/static/${game.path}/$resource"), res.getOutputStream())
  }
}

object ContentTypeOf {
  def apply(resource: String) = {
    resource.drop(resource.lastIndexOf(".") + 1) match {
      case "png" => "image/png"
      case _ => "application/octet-stream"
    }
  }
}

object Expires {

  lazy val date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").format(anYearFromNow)

  private def anYearFromNow = new Date(new Date().getTime + 31535000000l)

}
