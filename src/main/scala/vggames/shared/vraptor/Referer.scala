package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest

@Component
class Referer(request : HttpServletRequest) {

  def url : String = Option(request.getHeader("Referer")).
    filter(_.startsWith(baseUrl)).getOrElse(baseUrl)

  def baseUrl = "http://" + request.getServerName
}