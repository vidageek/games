package vggames.regex

import vggames.shared.task.JudgedTask
import vggames.shared.task.Task

class Match(matchingTargets : MatcherTargets) extends Task {

  def judge(challenge : String) : JudgedTask = new Regex(challenge).matchAll(matchingTargets).judgment();

  def getChallenge : String = "Qual RegEx reconhece " + matchingTargets.asHtml() + "?"

  def resource = ""
}
