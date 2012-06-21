package vggames.regex.task;

import vggames.shared.task.JudgedTask;
import vggames.shared.task.Task;
import vggames.shared.task.status.Faileds;

class NegateAndFind(cannotMatch : MatcherTargets, mustMatch : MatcherTargets) extends Task {

  def judge(challenge : String) : JudgedTask = {
    val faileds = new Regex(challenge).findNone(cannotMatch);
    faileds.addAll(new Regex(challenge).matchAll(mustMatch));
    faileds.judgment();
  }

  def getChallenge : String = {
    "Qual RegEx n&atilde;o reconhece parcialmente" + cannotMatch.asHtml + " mas reconhece " + mustMatch.asHtml + "?";
  }

  override def toString : String = getChallenge
}
