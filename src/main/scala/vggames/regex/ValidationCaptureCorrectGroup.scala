package vggames.regex

import java.util.regex.Matcher
import vggames.shared.task.status.{ Ok, Failed }
import vggames.shared.task.JudgedTask

class ValidationCaptureCorrectGroup(matchingTarget : String) extends GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask = {
    if (!matchingTarget.equals(matcher.group(0))) {
      return new Failed("A regex " + challenge.asHtml() + " n&atilde;o reconhece a string "
        + matchingTarget.asHtml())
    }
    new Ok()
  }

}
