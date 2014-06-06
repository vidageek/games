package vggames.webdev

import vggames.shared.GameView
import vggames.shared.Game
import scalatags._
import vggames.shared.task.JudgedTask
import vggames.shared.task.Exercise

class WebdevGameView extends GameView {

  def render(game : Game, task : Exercise, judgedTask : Option[JudgedTask], lastAttempt : String) = {
    div("row".cls)(
      div("span6".cls)(
        judgement(judgedTask),
        singleLineChallengeForm(game, task, lastAttempt, "Next! (ctrl + enter)", "span5"),
        progressBar(task, game)),
      taskDescription(task, "span6")).toString
  }
}