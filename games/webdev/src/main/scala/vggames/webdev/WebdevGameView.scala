package vggames.webdev

import vggames.shared.GameView
import vggames.shared.Game
import scalatags.Text.all._
import vggames.shared.task.JudgedTask
import vggames.shared.task.Exercise

class WebdevGameView extends GameView {

  def render(game: Game, task: Exercise, judgedTask: Option[JudgedTask], lastAttempt: String) = {
    div(cls := "row")(
      div(cls := "span6")(
        judgement(judgedTask),
        singleLineChallengeForm(game, task, lastAttempt, "Next! (ctrl + enter)", "span5",
          "^https://github.com/[^/]+/.+$", "Url deve ter o padrão https://github.com/USUARIO/REPOSITORIO"),
        progressBar(task, game)),
      taskDescription(task, "span6")).toString
  }
}