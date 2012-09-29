package vggames.regex

import java.util.regex.Matcher
import vggames.shared.task.status.{ Ok, Failed }
import vggames.shared.task.JudgedTask

class ValidationIfMatch(matchingTarget : String) extends GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask = {
    if (!matcher.find()) {
      return new Failed("A regex " + challenge.asHtml() + " n&atilde;o d&aacute; match em "
        + matchingTarget.asHtml())
    }
    new Ok()
  }
}
