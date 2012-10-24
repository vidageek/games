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

  private def find(matchingTarget : String) : JudgedTask = {
    if (pattern.matcher(matchingTarget).find())
      new Ok()
    else
      new Failed(regex.asHtml() + " n&atilde;o reconhece parcialmente " + matchingTarget.asHtml())
  }

  def matchNone(cannotMatch : MatcherTargets) : Faileds = {
    cannotMatch.foldLeft(new Faileds()) { (fails, target) =>
      if (doMatch(target).ok) {
        fails.addOnlyJudgedFailed(new Failed("N&atilde;o deveria fazer match com " + target.asHtml()))
      }
      fails
    }
  }

  def findNone(cannotMatch : MatcherTargets) : Faileds = {
    cannotMatch.foldLeft(new Faileds()) { (fails, target) =>
      if (find(target).ok) {
        fails.addOnlyJudgedFailed(new Failed("N&atilde;o deveria reconhecer parcialmente " + target.asHtml()))
      }
      fails
    }
  }

  def matchAll(negateClassShouldMatch : MatcherTargets) : Faileds = {
    negateClassShouldMatch.foldLeft(new Faileds()) { (fails, target) =>
      fails.addOnlyJudgedFailed(doMatch(target))
      fails
    }
  }
}
