
package vggames.shared.vraptor

import br.com.caelum.vraptor.Intercepts
import br.com.caelum.vraptor.core.InterceptorStack
import br.com.caelum.vraptor.resource.ResourceMethod
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import vggames.shared.player.PlayerSession

@Intercepts
class DomainAspect(req: HttpServletRequest, res: HttpServletResponse) extends RequestAspect {

  override def isApplicable(method: ResourceMethod) = true

  override def apply(stack: InterceptorStack, method: ResourceMethod, resourceInstance : Any ) {
    if (req.getServerName() == "games.vidageek.net") {
      res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY)
      res.setHeader("Location", req.getRequestURL().toString().replace("games.vidageek.net", "aprenda.vidageek.net"))
    } else {
      stack.next(method, resourceInstance)
    }
  }

}
