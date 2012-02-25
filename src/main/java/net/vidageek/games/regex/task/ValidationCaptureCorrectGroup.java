package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

final public class ValidationCaptureCorrectGroup implements GroupValidation {

	private final String matchingTarget;

	public ValidationCaptureCorrectGroup(final String matchingTarget) {
		this.matchingTarget = matchingTarget;
	}

	public JudgedTask judge(final String challenge, final Matcher matcher) {
		if (!matchingTarget.equals(matcher.group(0))) {
			return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o reconhece a string "
					+ from(matchingTarget).asHtml());
		}
		return new Ok();
	}

}
