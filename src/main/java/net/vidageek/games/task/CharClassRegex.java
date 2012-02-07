package net.vidageek.games.task;

import net.vidageek.games.regex.task.MatcherTargets;
import net.vidageek.games.regex.task.Regex;

import com.google.common.base.Joiner;

public class CharClassRegex implements Task {

	private final MatcherTargets matchingTargets;

	public CharClassRegex(String... matchingTargets) {
		this.matchingTargets = MatcherTargets.fromStrings(matchingTargets);

	}

	public JudgedTask judge(String challenge) {
		return new Regex(challenge).matchAll(this.matchingTargets).judgment();
	}

	public String getDescription() {
		return "Matching com classes de caracteres";
	}

	public String getChallenge() {
		return toString();
	}

	@Override
	public String toString() {
		return "Qual regex dá match em [" + matchingTargets() + "]";
	}

	private String matchingTargets() {
		return Joiner.on(" e ").join(this.matchingTargets);
	}
	
	
}
