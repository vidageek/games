package vggames.html

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.JudgedTask
import scalatags._
import vggames.shared.GameResourceId
import vggames.shared.task.Exercise

class HtmlGameView extends GameView {

  def render(game : Game, task : Exercise, judgedTask : Option[JudgedTask], lastAttempt : String) = {

    val prefill = task.extraData.asInstanceOf[Option[String]].getOrElse("")

    div("row".cls)(
      div("span6".cls)(

        iframe(id := "render-challenge", "game-frame".cls)(raw("")),

        div(id := "challenge-result")(raw("")),
        multiLineChallengeForm(game, task, prefill, true, "disabled"),
        progressBar(task, game)),

      div("span6".cls)(
        iframe(id := "render-answer", "game-frame".cls, src := s"/play/html/resource/${task.resource}?v=${GameResourceId.id}")(""),

        h2(task.groupName),
        raw(task.description))).toString
  }
}
