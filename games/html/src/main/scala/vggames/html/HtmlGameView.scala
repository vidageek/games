package vggames.html

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.TaskWithDescription
import vggames.shared.task.JudgedTask
import scalatags._

class HtmlGameView extends GameView {
  def render(game : Game, task : TaskWithDescription, judgedTask : Option[JudgedTask], lastAttempt : String) = {

    div("row".cls)(
      div("span6".cls)(

        iframe(id := "render-challenge", "game-frame".cls)(raw("")),

        div(id := "challenge-result")(raw("")),
        multiLineChallengeForm(game, task, lastAttempt, true, "disabled"),
        progressBar(task, game)),

      div("span6".cls)(
        iframe(id := "render-answer", "game-frame".cls, src := s"/play/html/resource/${task.resource}")(""),

        h2(task.groupName),
        raw(task.description))).toString
  }
}