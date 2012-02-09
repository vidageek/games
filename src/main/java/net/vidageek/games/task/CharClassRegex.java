package net.vidageek.games.task;

import net.vidageek.games.regex.task.MatcherTargets;
import net.vidageek.games.regex.task.Regex;

import com.google.common.base.Joiner;

public class CharClassRegex implements Task {

	private final MatcherTargets matchingTargets;

	public CharClassRegex(final MatcherTargets matchingTargets) {
		this.matchingTargets = matchingTargets;

	}

	public JudgedTask judge(final String challenge) {
		return new Regex(challenge).matchAll(matchingTargets).judgment();
	}

	public String getChallenge() {
		return toString();
	}

	@Override
	public String toString() {
		return "Qual regex d&aacute; match em [" + matchingTargets() + "]";
	}

	private String matchingTargets() {
		return Joiner.on(" e ").join(matchingTargets);
	}

}
