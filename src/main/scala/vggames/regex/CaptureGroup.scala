package vggames.regex

import java.util.regex.{ Matcher, Pattern }

import scala.collection.mutable.ListBuffer

import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.task.status.Ok

class CaptureGroup(val matchingTarget : String, val captureGroupTargets : String*) extends Task {

  val validations = List[GroupValidation](
    new ValidationIfMatch(matchingTarget),
    new ValidationCaptureCorrectGroup(matchingTarget),
    new ValidationIfAllGroupsMatch(captureGroupTargets.toList : _*))

  def judge(challenge : String) : JudgedTask = {
    val matcher = Pattern.compile(challenge).matcher(matchingTarget);
    return applyAllValidations(challenge, matcher);
  }

  def applyAllValidations(challenge : String, matcher : Matcher) : JudgedTask = {
    validations.foldLeft[JudgedTask](new Ok())((judge, validation) =>
      judge.getOk match {
        case true => validation.judge(challenge, matcher)
        case false => judge
      })
  }

  def getChallenge = "Qual regex d&aacute; match em " + matchingTarget.asHtml() + " e captura " + captureTarget + "?"

  def captureTarget : String = captureGroupTargets.asHtml()
}
