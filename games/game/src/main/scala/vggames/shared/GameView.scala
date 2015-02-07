package vggames.shared

import vggames.shared.task.JudgedTask
import scalatags.Text.all._
import vggames.shared.task.Task
import vggames.shared.task.Exercise

trait GameView {

  def render(game: Game, task: Exercise, judgedTask: Option[JudgedTask], lastAttempt: String): String

  def progressBar(task: Exercise, game: Game) =
    div(cls := "progress")(
      div(cls := "bar", style := s"width: ${(task.index.toDouble / game.size) * 100}%;")(""))

  def judgement(judgedTask: Option[JudgedTask]) =
    judgedTask.map { task =>
      div(id := "challenge-result", cls := s"reason alert ${if (task.ok) "alert-success" else "alert-error"}")(
        raw(task.reason))
    }.getOrElse(raw(""))

  def singleLineChallengeForm(game: Game, task: Exercise, lastAttempt: String, buttonText: String, extraInputClass: String,
    validationPattern: String = ".*", title: String = "") =
    form(cls := "challenge", "method".attr := "POST", action := s"/aprenda/${game.path}/task/${task.index}")(
      label("for".attr := "challenge")(strong(raw(task.challenge))),
      input(cls := s"focus $extraInputClass", name := "challenge", id := "challenge", autocomplete := "off",
        value := lastAttempt, "pattern".attr := validationPattern, "title".attr := title),
      input(id := "challenge-submit", cls := "btn btn-primary", "type".attr := "submit", value := buttonText))

  def multiLineChallengeForm(game: Game, task: Exercise, lastAttempt: String, buttonDisabled: Boolean, buttonExtraClass: String = "") = {
    form(cls := "challenge", "method".attr := "POST", action := s"/aprenda/${game.path}/task/${task.index}")(
      label("for".attr := "challenge")(strong(raw(task.challenge))),
      textarea(cls := "focus span6", name := "challenge", id := "challenge", autocomplete := "off")(
        s"\n${lastAttempt}"),
      if (buttonDisabled)
        input(id := "challenge-submit", cls := s"btn btn-primary $buttonExtraClass", "type".attr := "submit", value := "Check! ( ctrl + Enter )", disabled := true)
      else
        input(id := "challenge-submit", cls := s"btn btn-primary $buttonExtraClass", "type".attr := "submit", value := "Check! ( ctrl + Enter )"))
  }

  def taskDescription(task: Exercise, divClass: String) =
    div(cls := divClass)(
      h2(task.groupName),
      raw(task.description))

}
