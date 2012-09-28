package vggames.regex

import java.util.regex.{ Matcher, Pattern }

import scala.collection.mutable.ListBuffer

import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.task.status.Ok
import vggames.regex.MatcherTargets._

class CaptureGroup(val matchingTarget : String, val captureGroupTargets : String*) extends Task {

  val validations = ListBuffer[GroupValidation]()
  addAllValidations()

  def addAllValidations() : Unit = {
    validations += new ValidationIfMatch(matchingTarget)
    validations += new ValidationCaptureCorrectGroup(matchingTarget)
    validations += new ValidationIfAllGroupsMatch(captureGroupTargets.toList : _*)
  }

  def judge(challenge : String) : JudgedTask = {
    val matcher : Matcher = Pattern.compile(challenge).matcher(matchingTarget);
    return applyAllValidations(challenge, matcher);
  }

  def applyAllValidations(challenge : String, matcher : Matcher) : JudgedTask = {
    validations.foldLeft[JudgedTask](new Ok())((judge, validation) =>
      judge.getOk match {
        case true => validation.judge(challenge, matcher)
        case false => judge
      })
  }

  def getChallenge = "Qual regex d&aacute; match em " + from(matchingTarget).asHtml() + " e captura " + captureTarget + "?"

  def captureTarget : String = from(captureGroupTargets.toList : _*).asHtml()

  override def toString() = getChallenge

}
