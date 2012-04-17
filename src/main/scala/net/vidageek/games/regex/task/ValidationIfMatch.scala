package net.vidageek.games.regex.task;

import net.vidageek.games.regex.task.MatcherTargets._

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

class ValidationIfMatch(matchingTarget:String) extends GroupValidation {

	def judge(challenge: String, matcher:Matcher): JudgedTask = {
		if (!matcher.find()) {
			return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o d&aacute; match em "
					+ from(matchingTarget).asHtml())
		}
		new Ok()
	}

}
