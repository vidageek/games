package net.vidageek.games.regex.task

import java.util.regex.Pattern
import net.vidageek.games.task.JudgedTask
import net.vidageek.games.task.status.Failed
import net.vidageek.games.task.status.Faileds
import net.vidageek.games.task.status.Ok
import net.vidageek.games.regex.task.MatcherTargets._
import scala.collection.JavaConverters._

class Regex(regex: String) {
  val pattern = Pattern.compile(regex)

  def doMatch(matchingTarget: String): JudgedTask = {
    if (pattern.matcher(matchingTarget).matches())
      new Ok()
    else
      new Failed(from(regex).asHtml() + " n&atilde;o d&aacute; match em " + from(matchingTarget).asHtml())
  }

  def group(position: Int): GroupFinder = new GroupFinder(position, pattern)

  def matchAll(negateClassShouldMatch: MatcherTargets): Faileds = {
    val fails = new Faileds()
    negateClassShouldMatch.asScala foreach {matchingTarget =>
      fails.addOnlyJudgedFailed(doMatch(matchingTarget))
    }
    fails
  }

  private def find(matchingTarget: String): JudgedTask = {
    if (pattern.matcher(matchingTarget).find())
      new Ok()
    else 
      new Failed(from(regex).asHtml() + " n&atilde;o reconhece parcialmente " + from(matchingTarget).asHtml())
  }

  def matchNone(cannotMatch: MatcherTargets): Faileds = {
    val faileds = new Faileds()
    cannotMatch.asScala foreach {matchingTarget => 
      if (doMatch(matchingTarget).getOk()) {
        faileds.addOnlyJudgedFailed(new Failed("N&atilde;o deveria fazer match com " + from(matchingTarget).asHtml()))
      }
    }
    faileds
  }

  def findNone(cannotMatch: MatcherTargets): Faileds = {
    val faileds = new Faileds()
    cannotMatch.asScala foreach {matchingTarget =>
      if (find(matchingTarget).getOk()) {
        faileds.addOnlyJudgedFailed(new Failed("N&atilde;o deveria reconhecer parcialmente " + from(matchingTarget).asHtml()))
      }
    }
    faileds
  }
}
