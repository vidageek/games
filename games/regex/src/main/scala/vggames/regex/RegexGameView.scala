package vggames.regex

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.JudgedTask
import scalatags._
import vggames.shared.task.IndexedTask

class RegexGameView extends GameView {

  def render(game : Game, task : IndexedTask, judgedTask : Option[JudgedTask], lastAttempt : String) = {

    div("row".cls)(
      div("span5".cls)(
        judgement(judgedTask),
        singleLineChallengeForm(game, task, lastAttempt, "Check!", "span4"),
        progressBar(task, game)),
      taskDescription(task, "span7")).toString()
  }
}