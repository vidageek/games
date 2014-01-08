package vggames.regex

import vggames.shared.task.{ Task, JudgedTask }

class NegateAndMatch(cannotMatch : MatcherTargets, shouldMatch : MatcherTargets) extends Task[Any] {

  def judge(challenge : String) : JudgedTask = {
    val faileds = new Regex(challenge).matchNone(cannotMatch)
    faileds.addAll(new Regex(challenge).matchAll(shouldMatch))
    faileds.judgment()
  }

  def getChallenge =
    "Qual RegEx n&atilde;o reconhece " + cannotMatch.asHtml + " mas reconhece " + shouldMatch.asHtml + "?";

  def resource = ""
}
