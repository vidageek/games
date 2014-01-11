package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest
import vggames.shared.task.JudgedTask

@Component
class Params(request : HttpServletRequest) {

  def gameEnded = attr[String]("gameEnded")

  def judgedTask = attr[JudgedTask]("judgedTask")

  def lastAttempt = attr[String]("lastAttempt").getOrElse("")

  private def attr[T](name : String) = Option(request.getAttribute(name).asInstanceOf[T])

}