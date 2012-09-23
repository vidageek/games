package vggames.regex

import java.util.regex.Matcher
import vggames.shared.task.status.{ Ok, Failed }
import vggames.shared.task.JudgedTask

import vggames.regex.MatcherTargets._

class ValidationCaptureCorrectGroup(matchingTarget : String) extends GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask = {
    if (!matchingTarget.equals(matcher.group(0))) {
      return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o reconhece a string "
        + from(matchingTarget).asHtml())
    }
    new Ok()
  }

}
