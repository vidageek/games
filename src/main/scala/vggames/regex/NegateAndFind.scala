package vggames.regex

import vggames.shared.task.{ Task, JudgedTask }

class NegateAndFind(cannotMatch : MatcherTargets, mustMatch : MatcherTargets) extends Task {

  def judge(challenge : String) : JudgedTask = {
    val faileds = new Regex(challenge).findNone(cannotMatch);
    faileds.addAll(new Regex(challenge).matchAll(mustMatch));
    faileds.judgment();
  }

  def getChallenge =
    "Qual RegEx n&atilde;o reconhece parcialmente" + cannotMatch.asHtml + " mas reconhece " + mustMatch.asHtml + "?";

  override def toString = getChallenge
}
