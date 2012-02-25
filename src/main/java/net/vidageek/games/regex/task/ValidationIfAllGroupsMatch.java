package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

final public class ValidationIfAllGroupsMatch implements GroupValidation {

	private final String[] captureGroupTargets;

	public ValidationIfAllGroupsMatch(final String... captureGroupTargets) {
		this.captureGroupTargets = captureGroupTargets;

	}

	public JudgedTask judge(final String challenge, final Matcher matcher) {
		int i = 1;
		for (String target : captureGroupTargets) {
			if (!target.equals(matcher.group(i++))) {
				return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o captura a string "
						+ from(target).asHtml());
			}
		}
		return new Ok();
	}

}
