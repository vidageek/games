package net.vidageek.games.regex.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;

public class MatcherTargets {

	private List<String> matcherTargets = new ArrayList<String>();

	public MatcherTargets(String aMatcherTarget, String... othersMatchersTargets) {
		matcherTargets.add(aMatcherTarget);
		matcherTargets.addAll(Arrays.asList(othersMatchersTargets));
	}

	public String showMessages() {
		return Joiner.on(" e ").join(matcherTargets);
	}

}
