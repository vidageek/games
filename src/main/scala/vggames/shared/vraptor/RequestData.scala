package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest

@Component
class RequestData(request : HttpServletRequest) {

  private val gameRegex = "/(?:play|reference)/([^/]+)/?.*".r

  val game = gameRegex.findFirstMatchIn(request.getRequestURI).map(_.group(1)).getOrElse("")

}