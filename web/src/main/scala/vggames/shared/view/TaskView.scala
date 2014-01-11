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

class TaskView extends TypedView[(String, TaskWithDescription, Game)] {

  override def render(t : (String, TaskWithDescription, Game)) = {
    val (gameName, task, game) = t

    html(
      head(
        Tags.title(s"Exercício ${task.getIndex} do ${game.getName} game"),
        meta(name := "robots", content := "noindex")),
      body(
        raw(renderGameView(game, task))))
  }

  private def renderGameView(game : Game, task : TaskWithDescription) : String = {
    val viewName = s"vggames.${game.path}.${game.getName}GameView"

    Try(Class.forName(viewName).newInstance.asInstanceOf[GameView]).
      map(_.render(game, task)) match {
        case Success(string) => string
        case Failure(t) => s"Não foi encontrada view para o jogo ${game.getName}. " +
        s"Exceção: ${t.getClass.getName} ${t.getMessage} <pre>${t.getStackTraceString}</pre>"
      }
  }
}