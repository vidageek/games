package net.vidageek.games.regex;

import java.util.regex.Pattern;

import net.vidageek.games.regex.task.Failed;
import net.vidageek.games.regex.task.Faileds;
import net.vidageek.games.regex.task.Ok;
import net.vidageek.games.task.JudgedTask;

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
		return pattern.matcher(matchingTarget).matches()? new Ok() : new Failed("[" + this.pattern.pattern() + "] não dá match em [" + matchingTarget + "]");
	}

	public GroupFinder group(final int position) {
		return new GroupFinder(position, pattern);
	}

	public JudgedTask matchAll(String[] matchingTargets) {
		Faileds fails = new Faileds();
		for (String matchingTarget : matchingTargets) {
			fails.addOnlyJudgedFailed(match(matchingTarget));
		}
		return fails.ok() ? new Ok() : fails;
	}

}
