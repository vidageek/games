package vggames.shared

import vggames.shared.task.TaskWithDescription
import vggames.shared.task.JudgedTask
import scalatags._
import vggames.shared.task.Task

trait GameView {

  def render(game : Game, task : TaskWithDescription, judgedTask : Option[JudgedTask], lastAttempt : String) : String

  def progressBar(task : TaskWithDescription, game : Game) =
    div("progress".cls)(
      div("bar".cls, style := s"width: ${(task.getIndex.toDouble / game.getSize) * 100}%;")(""))

  def judgement(judgedTask : Option[JudgedTask]) =
    judgedTask.map { task =>
      div(id := "challenge-result", s"reason alert ${if (task.ok) "alert-success" else "alert-error"}".cls)(
        raw(task.reason))
    }.getOrElse(raw(""))

  def singleLineChallengeForm(game : Game, task : TaskWithDescription, lastAttempt : String, buttonText : String, extraInputClass : String) =
    form("challenge".cls, "method".attr := "POST", action := s"/play/${game.path}/task/${task.getIndex}")(
      label("for".attr := "challenge")(strong(raw(task.challenge))),
      input(s"focus $extraInputClass".cls, name := "challenge", id := "challenge", autocomplete := "off", value := lastAttempt),
      input(id := "challenge-submit", "btn btn-primary".cls, "type".attr := "submit", value := buttonText))

  def multiLineChallengeForm(game : Game, task : TaskWithDescription, lastAttempt : String, buttonDisabled : Boolean, buttonExtraClass : String = "") = {
    form("challenge".cls, "method".attr := "POST", action := s"/play/${game.path}/task/${task.getIndex}")(
      label("for".attr := "challenge")(strong(raw(task.challenge))),
      textarea("focus span6".cls, name := "challenge", id := "challenge", autocomplete := "off")(
        s"\n${lastAttempt}"),
      if (buttonDisabled)
        input(id := "challenge-submit", s"btn btn-primary $buttonExtraClass".cls, "type".attr := "submit", value := "Check! ( ctrl + Enter )", disabled := true)
      else
        input(id := "challenge-submit", s"btn btn-primary $buttonExtraClass".cls, "type".attr := "submit", value := "Check! ( ctrl + Enter )"))
  }

  def taskDescription(task : TaskWithDescription, divClass : String) =
    div(divClass.cls)(
      h2(task.getGroupName),
      raw(task.getDescription))

}