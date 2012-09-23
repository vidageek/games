package vggames.regex

import java.util.regex.Matcher
import vggames.shared.task.status.{ Ok, Failed }
import vggames.shared.task.JudgedTask
import vggames.regex.MatcherTargets._

class ValidationIfMatch(matchingTarget : String) extends GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask = {
    if (!matcher.find()) {
      return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o d&aacute; match em "
        + from(matchingTarget).asHtml())
    }
    new Ok()
  }

}
