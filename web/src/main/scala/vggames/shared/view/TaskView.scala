package vggames.shared.view

import vggames.shared.GamesConfiguration
import vggames.shared.Game
import scala.collection.concurrent.Map
import scalatags._
import vggames.shared.vraptor.GameFactoryCache
import vggames.shared.task.TaskWithDescription
import scala.util.Try
import vggames.shared.GameView
import scala.util.Success
import scala.util.Failure
import vggames.shared.task.JudgedTask

class TaskView extends TypedView[(String, TaskWithDescription, Game, Option[JudgedTask], String)] {

  override def render(t : (String, TaskWithDescription, Game, Option[JudgedTask], String)) = {
    val (gameName, task, game, judgedTask, lastAttempt) = t

    html(
      head(
        Tags.title(s"Exercício ${task.index} do ${game.name} game"),
        meta(name := "robots", content := "noindex")),
      body(
        raw(renderGameView(game, task, judgedTask, lastAttempt))))
  }

  private def renderGameView(game : Game, task : TaskWithDescription, judgedTask : Option[JudgedTask], lastAttempt : String) : String = {
    val viewName = s"vggames.${game.path}.${game.path.capitalize}GameView"

    Try(Class.forName(viewName).newInstance.asInstanceOf[GameView]).
      map(_.render(game, task, judgedTask, lastAttempt)) match {
        case Success(string) => string
        case Failure(t) => s"Não foi encontrada view para o jogo ${game.name}. " +
        s"Exceção: ${t.getClass.getName} ${t.getMessage} <pre>${t.getStackTraceString}</pre>"
      }
  }
}