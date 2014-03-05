package vggames.sql

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.JudgedTask
import vggames.shared.task.TaskWithDescription
import scalatags._

class SqlGameView extends GameView {

  def render(game : Game, task : TaskWithDescription, judgedTask : Option[JudgedTask], lastAttempt : String) = {
    div("row".cls)(
      div("span6".cls)(
        judgement(judgedTask),
        multiLineChallengeForm(game, task, lastAttempt, true),
        progressBar(task, game)),
      taskDescription(task, "span6")).toString
  }

}