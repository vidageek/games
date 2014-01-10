package vggames.shared.vraptor

import br.com.caelum.vraptor.Result
import vggames.shared.view.TypedView
import br.com.caelum.vraptor.view.Results
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.View
import javax.servlet.http.HttpServletResponse
import java.nio.charset.Charset
import java.io.PrintWriter

object VraptorExtensions {

  implicit class AddRender(val result : Result) extends AnyVal {
    def render[O](view : TypedView[O])(tuple : O) = {
      result.use(classOf[GameView]).render(view.render(tuple).toString)
    }
  }
}

@Component
class GameView(response : HttpServletResponse) extends View {

  def render(html : String) = {
    response.setContentType("text/html")
    response.setCharacterEncoding("UTF-8")
    response.getOutputStream().write(html.getBytes(Charset.forName("UTF-8")))
  }

}