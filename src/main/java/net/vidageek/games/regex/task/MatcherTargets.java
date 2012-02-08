package net.vidageek.games.regex.task;

import static java.util.Arrays.copyOfRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;

public class MatcherTargets implements Iterable<String> {

	private List<String> matcherTargets = new ArrayList<String>();

	private MatcherTargets(String aMatcherTarget, String... othersMatchersTargets) {
		matcherTargets.add(aMatcherTarget);
		matcherTargets.addAll(Arrays.asList(othersMatchersTargets));
	}

	public String showMessages() {
		return "\"" + Joiner.on("\" e \"").join(matcherTargets) + "\"";
	}

	public Iterator<String> iterator() {
		return Collections.unmodifiableCollection(matcherTargets).iterator();
	}

	public static MatcherTargets fromStrings(String... matchingTargets) {
		return new MatcherTargets(matchingTargets[0], copyOfRange(matchingTargets, 1, matchingTargets.length));
	}

}
