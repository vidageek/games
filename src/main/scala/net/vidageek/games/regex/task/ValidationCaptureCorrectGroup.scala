package net.vidageek.games.regex.task;

import net.vidageek.games.regex.task.MatcherTargets._

import java.util.regex.Matcher

import net.vidageek.games.task.JudgedTask
import net.vidageek.games.task.status.Failed
import net.vidageek.games.task.status.Ok

class ValidationCaptureCorrectGroup(matchingTarget: String) extends GroupValidation {

	def judge(challenge: String, matcher: Matcher): JudgedTask = {
		if (!matchingTarget.equals(matcher.group(0))) {
			return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o reconhece a string "
					+ from(matchingTarget).asHtml())
		}
		new Ok()
	}

}
