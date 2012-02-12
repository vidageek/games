package net.vidageek.games.regex.task;

import static java.util.Arrays.copyOfRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;

public class MatcherTargets implements Iterable<String> {

	private final List<String> matcherTargets = new ArrayList<String>();

	private MatcherTargets(final String aMatcherTarget, final String... othersMatchersTargets) {
		matcherTargets.add(aMatcherTarget);
		matcherTargets.addAll(Arrays.asList(othersMatchersTargets));
	}

	public String showMessages() {
		return "\"" + Joiner.on("\" e \"").join(matcherTargets) + "\"";
	}

	public Iterator<String> iterator() {
		return Collections.unmodifiableCollection(matcherTargets).iterator();
	}

	public static MatcherTargets fromStrings(final String... matchingTargets) {
		return new MatcherTargets(matchingTargets[0], copyOfRange(matchingTargets, 1, matchingTargets.length));
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
