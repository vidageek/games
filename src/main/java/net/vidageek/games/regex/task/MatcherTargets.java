package net.vidageek.games.regex.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;

public class MatcherTargets implements Iterable<String> {

	private final List<String> matcherTargets = new ArrayList<String>();

	private MatcherTargets(final String... othersMatchersTargets) {
		matcherTargets.addAll(Arrays.asList(othersMatchersTargets));
	}

	public Iterator<String> iterator() {
		return Collections.unmodifiableCollection(matcherTargets).iterator();
	}

	public static MatcherTargets from(final String... matchingTargets) {
		return new MatcherTargets(matchingTargets);
	}

	public String asHtml() {
		return swapLastComma("<code>" + Joiner.on("</code>, <code>").join(this) + "</code>");
	}

	private String swapLastComma(final String string) {
		for (int i = string.length() - 1; i >= 0; i--) {
			if (string.charAt(i) == ',') {
				return string.substring(0, i) + " e" + string.substring(i + 1);
			}
		}
		return string;
	}

}
