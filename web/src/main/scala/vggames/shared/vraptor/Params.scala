package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest
import vggames.shared.task.JudgedTask

@Component
class Params(request : HttpServletRequest, data : RequestData, cached : GameFactoryCache) {

  def gameEnded = attr[String]("gameEnded")

  def judgedTask = attr[JudgedTask]("judgedTask")

  def activeTime = param[String]("activeTime")

  def lastAttempt = attr[String]("lastAttempt").getOrElse("")

  def notice = attr[String]("notice")

  def gameId = data.game

  def game = cached(gameId)

  private def attr[T](name : String) = Option(request.getAttribute(name).asInstanceOf[T])

  private def param[T](name : String) = Option(request.getParameter(name).asInstanceOf[T])

}