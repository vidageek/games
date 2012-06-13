package net.vidageek.games.regex.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jonasabreu
 * 
 */
final public class GroupFinder {

	private final int position;
	private final Pattern pattern;

	public GroupFinder(final int position, final Pattern pattern) {
		this.position = position;
		this.pattern = pattern;
	}

	public String from(final String text) {
		final Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			return matcher.group(position);
		} else {
			throw new IllegalArgumentException("could not match text.");
		}
	}
}
