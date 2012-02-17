package net.vidageek.games.regex.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class MatcherTargets implements Iterable<String> {

	private final Escaper escape = new Escaper();
	private final List<String> matcherTargets = Lists.newArrayList();

	private MatcherTargets(final String... othersMatchersTargets) {
		matcherTargets.addAll(Arrays.asList(othersMatchersTargets));
	}

	public static MatcherTargets from(final String... matchingTargets) {
		return new MatcherTargets(matchingTargets);
	}

	private List<String> scapeTarges() {
		return escape.applyAll(matcherTargets);
	}

	public Iterator<String> iterator() {
		return Collections.unmodifiableCollection(matcherTargets).iterator();
	}

	public String asHtml() {
		return swapLastComma("<code>" + Joiner.on("</code>, <code>").join(scapeTarges()) + "</code>");
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
