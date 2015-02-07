package vggames.html

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.JudgedTask
import scalatags.Text.all._
import vggames.shared.GameResourceId
import vggames.shared.task.Exercise

class HtmlGameView extends GameView {

  def render(game: Game, task: Exercise, judgedTask: Option[JudgedTask], lastAttempt: String) = {

    val data = task.extraData.asInstanceOf[Option[HtmlExtraData]].get

    div(cls := "row")(
      div(cls := "span6")(

        iframe(id := "render-challenge", cls := "game-frame")(raw("")),

        div(id := "challenge-result")(raw("")),
        multiLineChallengeForm(game, task, data.prefill.getOrElse(""), true, "disabled"),
        progressBar(task, game)),

      div(cls := "span6")(
        iframe(id := "render-answer", cls := "game-frame", "data-before".attr := data.context.before, "data-after".attr := data.context.after, "data-src".attr := s"/aprenda/html/resource/${task.resource}?v=${GameResourceId.id}")(""),

        h2(task.groupName),
        raw(task.description))).toString
  }
}
