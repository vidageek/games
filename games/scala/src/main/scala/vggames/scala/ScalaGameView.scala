package vggames.scala

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.JudgedTask
import scalatags._
import vggames.shared.task.Exercise

class ScalaGameView extends GameView {

  def render(game : Game, task : Exercise, judgedTask : Option[JudgedTask], lastAttempt : String) = {

    div("row".cls)(
      div("span6".cls)(
        judgement(judgedTask),
        multiLineChallengeForm(game, task, lastAttempt, false),
        progressBar(task, game)),
      taskDescription(task, "span6")).toString
  }
}