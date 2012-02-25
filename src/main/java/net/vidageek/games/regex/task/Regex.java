package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;

import java.util.regex.Pattern;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Faileds;
import net.vidageek.games.task.status.Ok;

/**
 * @author jonasabreu
 * 
 */
final public class Regex {

	private final Pattern pattern;
	private final String originalRegex;

	public Regex(final String regex) {
		originalRegex = regex;
		pattern = Pattern.compile(regex);
	}

	public JudgedTask match(final String matchingTarget) {
		if (pattern.matcher(matchingTarget).matches()) {
			return new Ok();
		}
		return new Failed("\"" + from(originalRegex).asHtml() + "\" n&atilde;o d&aacute; match em \""
				+ from(matchingTarget).asHtml() + "\"");
	}

	public GroupFinder group(final int position) {
		return new GroupFinder(position, pattern);
	}

	public Faileds matchAll(final MatcherTargets negateClassShouldMatch) {
		Faileds fails = new Faileds();
		for (String matchingTarget : negateClassShouldMatch) {
			fails.addOnlyJudgedFailed(match(matchingTarget));
		}
		return fails;
	}

}
