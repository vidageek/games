package vggames.sql

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.JudgedTask
import scalatags.Text.all._
import vggames.shared.task.Exercise

class SqlGameView extends GameView {

  def render(game: Game, task: Exercise, judgedTask: Option[JudgedTask], lastAttempt: String) = {
    div(cls := "row")(
      div(cls := "span6")(
        judgement(judgedTask),
        multiLineChallengeForm(game, task, lastAttempt, false),
        progressBar(task, game)),
      taskDescription(task, "span6")).toString
  }

}