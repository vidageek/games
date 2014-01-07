package vggames.shared.player

import br.com.caelum.vraptor.Intercepts
import br.com.caelum.vraptor.interceptor.Interceptor
import br.com.caelum.vraptor.core.InterceptorStack
import br.com.caelum.vraptor.resource.ResourceMethod
import javax.servlet.http.HttpServletRequest

@Intercepts
class PlayerInterceptor(session : PlayerSession, request : HttpServletRequest) extends Interceptor {

  def intercept(stack : InterceptorStack, method : ResourceMethod, resourceInstance : Object) = {
    session.actualPlayer.map(request.setAttribute("player", _))
    stack.next(method, resourceInstance)
  }

  def accepts(method : ResourceMethod) = true
}