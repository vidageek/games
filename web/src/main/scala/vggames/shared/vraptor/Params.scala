package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest

@Component
class Params(request : HttpServletRequest) {

  def gameEnded = Option(request.getParameter("gameEnded"))

}