package net.vidageek.games.regex.task;

import net.vidageek.games.regex.task.MatcherTargets._

import java.util.regex.Matcher

import net.vidageek.games.task.JudgedTask
import net.vidageek.games.task.status.Failed
import net.vidageek.games.task.status.Ok

class ValidationIfAllGroupsMatch(captureGroupTargets: String*) extends GroupValidation {

  def judge(challenge: String, matcher: Matcher): JudgedTask = {
    var i = 1
    for (target <- captureGroupTargets.toList) {
      if (!target.equals(matcher.group(i))) {
        return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o captura a string " + from(target).asHtml())
      }
      i+=1
    }
    new Ok()
  }

}
