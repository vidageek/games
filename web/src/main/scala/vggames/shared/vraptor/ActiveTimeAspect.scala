package vggames.shared.vraptor

import br.com.caelum.vraptor.Intercepts
import br.com.caelum.vraptor.interceptor.Interceptor
import br.com.caelum.vraptor.core.InterceptorStack
import br.com.caelum.vraptor.resource.ResourceMethod
import javax.servlet.http.HttpServletRequest
import vggames.shared.player.PlayerSession

@Intercepts
class ActiveTimeAspect(params : Params, session : PlayerSession) extends RequestAspect {

  override def apply(stack : InterceptorStack, method : ResourceMethod, resourceInstance : Any) {
    try {
      params.activeTime.map { activeTime =>
        var time = activeTime.toLong
        if (time > 30) time = 30
        if (time < 0) time = 0

        session.addActiveTime(time)
      }
    } finally {
      stack.next(method, resourceInstance)
    }
  }

  override def isApplicable(method : ResourceMethod) = true

}