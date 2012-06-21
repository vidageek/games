package vggames.regex.task;

import vggames.regex.task.MatcherTargets._
import java.util.regex.Matcher
import vggames.shared.task.status.Ok
import vggames.shared.task.JudgedTask
import vggames.shared.task.status.Failed

class ValidationIfAllGroupsMatch(captureGroupTargets : String*) extends GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask = {
    var i = 1
    for (target <- captureGroupTargets.toList) {
      if (!target.equals(matcher.group(i))) {
        return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o captura a string " + from(target).asHtml())
      }
      i += 1
    }
    new Ok()
  }

}
