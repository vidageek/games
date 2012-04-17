package net.vidageek.games.regex.task;

import net.vidageek.games.regex.task.MatcherTargets._;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

class ValidationIfAllCapture(captureGroupTargets: String*) extends GroupValidation {

	def judge(challenge: String, matcher: Matcher): JudgedTask = {
		if (matcher.groupCount() != captureGroupTargets.length) {
			return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o cont&eacute;m "
					+ captureGroupTargets.length + " grupo(s) de captura.");
		}
		return new Ok();
	}

}
