package vggames.regex

import java.util.regex.Pattern
import vggames.shared.task.status.{ Ok, Faileds, Failed }
import vggames.shared.task.JudgedTask

class Regex(regex : String) {
  val pattern = Pattern.compile(regex)

  def doMatch(matchingTarget : String) : JudgedTask = {
    if (pattern.matcher(matchingTarget).matches())
      new Ok()
    else
      new Failed(regex.asHtml() + " n&atilde;o d&aacute; match em " + matchingTarget.asHtml())
  }

  def group(position : Int) : GroupFinder = new GroupFinder(position, pattern)

  def matchAll(negateClassShouldMatch : MatcherTargets) : Faileds = {
    val fails = new Faileds()
    negateClassShouldMatch foreach { matchingTarget =>
      fails.addOnlyJudgedFailed(doMatch(matchingTarget))
    }
    fails
  }

  private def find(matchingTarget : String) : JudgedTask = {
    if (pattern.matcher(matchingTarget).find())
      new Ok()
    else
      new Failed(regex.asHtml() + " n&atilde;o reconhece parcialmente " + matchingTarget.asHtml())
  }

  def matchNone(cannotMatch : MatcherTargets) : Faileds = {
    val faileds = new Faileds()
    cannotMatch foreach { matchingTarget =>
      if (doMatch(matchingTarget).getOk) {
        faileds.addOnlyJudgedFailed(new Failed("N&atilde;o deveria fazer match com " + matchingTarget.asHtml()))
      }
    }
    faileds
  }

  def findNone(cannotMatch : MatcherTargets) : Faileds = {
    val faileds = new Faileds()
    cannotMatch foreach { matchingTarget =>
      if (find(matchingTarget).getOk) {
        faileds.addOnlyJudgedFailed(new Failed("N&atilde;o deveria reconhecer parcialmente " + matchingTarget.asHtml()))
      }
    }
    faileds
  }
}
