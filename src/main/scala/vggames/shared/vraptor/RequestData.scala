package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest
import br.com.caelum.vraptor.ioc.RequestScoped

@Component
class RequestData(request : HttpServletRequest) {

  private val gameRegex = "/play/([^/]+)/?.*".r

  val game : String = {
    val m = gameRegex.findFirstMatchIn(request.getRequestURI)
    if (m.isDefined) m.get.group(1) else ""
  }

}