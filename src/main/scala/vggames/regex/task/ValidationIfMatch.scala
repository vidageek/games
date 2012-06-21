package vggames.regex.task;

import vggames.regex.task.MatcherTargets._
import java.util.regex.Matcher;
import vggames.shared.task.status.Ok
import vggames.shared.task.JudgedTask
import vggames.shared.task.status.Failed

class ValidationIfMatch(matchingTarget : String) extends GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask = {
    if (!matcher.find()) {
      return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o d&aacute; match em "
        + from(matchingTarget).asHtml())
    }
    new Ok()
  }

}
