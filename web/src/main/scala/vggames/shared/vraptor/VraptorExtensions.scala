package vggames.shared.vraptor

import br.com.caelum.vraptor.Result
import vggames.shared.view.TypedView
import br.com.caelum.vraptor.view.Results
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.View
import javax.servlet.http.HttpServletResponse
import java.nio.charset.Charset
import java.io.PrintWriter
import br.com.caelum.vraptor.Intercepts
import javax.servlet.http.HttpServletRequest
import br.com.caelum.vraptor.interceptor.Interceptor
import br.com.caelum.vraptor.core.InterceptorStack
import br.com.caelum.vraptor.resource.ResourceMethod

object VraptorExtensions {

  implicit class AddRender(val result : Result) extends AnyVal {
    def render[O](view : TypedView[O])(tuple : O) = {
      result.use(classOf[GameTypedView]).render(view.renderString(tuple), view)
    }
  }
}

@Component
class GameTypedView(response : HttpServletResponse) extends View {

  def render(html : String, view : TypedView[_]) = {
    response.setContentType(view.contentType)
    response.setCharacterEncoding("UTF-8")
    response.getOutputStream().write(html.getBytes(Charset.forName("UTF-8")))
  }
}

@Intercepts
class AddGameInterceptor(req : HttpServletRequest, data : RequestData, cached : GameFactoryCache) extends Interceptor {

  override def intercept(stack : InterceptorStack, method : ResourceMethod, resourceInstance : Any) {
    if (!"".equals(data.game)) {
      req.setAttribute("gameName", data.game)
      req.setAttribute("game", cached(data.game).getOrElse(""))

    }
    stack.next(method, resourceInstance)
  }

  override def accepts(method : ResourceMethod) = true

}