package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

final public class ValidationIfMatch implements GroupValidation {

	private final String matchingTarget;

	public ValidationIfMatch(final String matchingTarget) {
		this.matchingTarget = matchingTarget;
	}

	public JudgedTask judge(final String challenge, final Matcher matcher) {
		if (!matcher.find()) {
			return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o d&aacute; match em "
					+ from(matchingTarget).asHtml());
		}
		return new Ok();
	}

}
