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
import java.util.zip.GZIPOutputStream
import java.io.ByteArrayOutputStream

object VraptorExtensions {

  implicit class AddRender(val result : Result) extends AnyVal {
    def render[O](view : TypedView[O])(tuple : O) = {
      result.use(classOf[GameTypedView]).render(view.renderString(tuple), view)
    }
  }
}

trait RequestAspect extends Interceptor {

  def apply(stack : InterceptorStack, method : ResourceMethod, resourceInstance : Any) : Unit

  def isApplicable(method : ResourceMethod) : Boolean

  final def intercept(stack : InterceptorStack, method : ResourceMethod, resourceInstance : Any) = apply(stack, method, resourceInstance)

  final def accepts(method : ResourceMethod) = isApplicable(method)

}

@Component
class GameTypedView(response : HttpServletResponse, decorate : Decoration, headers : Headers) extends View {

  def render(html : String, view : TypedView[_]) = {
    response.setContentType(view.contentType)
    response.setCharacterEncoding("UTF-8")

    val content = (if (view.contentType == "text/html")
      decorate(html) else html).
      getBytes(Charset.forName("UTF-8"))

    headers.acceptEncoding("gzip") match {
      case Some(_) =>
        response.addHeader("Content-Encoding", "gzip");
        val stream = new GZIPOutputStream(response.getOutputStream())
        stream.write(content)
        stream.close
      case None =>
        response.getOutputStream().write(content)
    }
  }
}
