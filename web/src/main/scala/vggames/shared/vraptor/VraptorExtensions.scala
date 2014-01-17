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
import vggames.shared.view.Decoration

object VraptorExtensions {

  implicit class AddRender(val result : Result) extends AnyVal {
    def render[O](view : TypedView[O])(tuple : O) = {
      result.use(classOf[GameTypedView]).render(view.renderString(tuple), view)
    }
  }
}

@Component
class GameTypedView(response : HttpServletResponse, decorate : Decoration) extends View {

  def render(html : String, view : TypedView[_]) = {
    response.setContentType(view.contentType)
    response.setCharacterEncoding("UTF-8")
    response.getOutputStream().write(
      (if (view.contentType == "text/html")
        decorate(html) else html).
        getBytes(Charset.forName("UTF-8")))
  }
}
