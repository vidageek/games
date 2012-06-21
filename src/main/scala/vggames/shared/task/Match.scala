package vggames.shared.task;

import vggames.regex.task.MatcherTargets;
import vggames.regex.task.Regex;

class Match(matchingTargets : MatcherTargets) extends Task {

  def judge(challenge : String) : JudgedTask = new Regex(challenge).matchAll(matchingTargets).judgment();

  def getChallenge : String = "Qual RegEx reconhece " + matchingTargets.asHtml() + "?"

  override def toString : String = getChallenge
}
