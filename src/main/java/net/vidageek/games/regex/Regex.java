package net.vidageek.games.regex;

import java.util.regex.Pattern;

import net.vidageek.games.regex.task.Failed;
import net.vidageek.games.regex.task.Faileds;

/**
 * @author jonasabreu
 * 
 */
final public class Regex {

	private final Pattern pattern;

	public Regex(final String regex) {
		pattern = Pattern.compile(regex);
	}

	public boolean match(final String text) {
		return pattern.matcher(text).matches();
	}

	public GroupFinder group(final int position) {
		return new GroupFinder(position, pattern);
	}

	public Faileds matchAll(String[] matchingTargets) {
		Faileds fails = new Faileds();
		for (String matchingTarget : matchingTargets) {
			if (!match(matchingTarget)) {
				fails.add(new Failed("[" + this.pattern.pattern() + "] não dá match em [" + matchingTarget + "]"));
			}
		}
		return fails;
	}

}
