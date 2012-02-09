package net.vidageek.games.regex.task;

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

	public Regex(final String regex) {
		pattern = Pattern.compile(regex);
	}

	public JudgedTask match(final String matchingTarget) {
		if (pattern.matcher(matchingTarget).matches()) {
			return new Ok();
		}
		return new Failed("\"" + pattern.pattern() + "\" n&atilde;o d&aacute; match em \"" + matchingTarget + "\"");
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
