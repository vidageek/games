package net.vidageek.games.task;

import net.vidageek.games.regex.task.MatcherTargets;
import net.vidageek.games.regex.task.Regex;

import com.google.common.base.Joiner;

public class MultipleMatch implements Task {

	private final MatcherTargets matchingTargets;

	public MultipleMatch(final MatcherTargets matchingTargets) {
		this.matchingTargets = matchingTargets;
	}

	public JudgedTask judge(final String challenge) {
		return new Regex(challenge).matchAll(matchingTargets).judgment();
	}

	public String getChallenge() {
		return "Qual regex reconhece " + matchingTargets() + "?";
	}

	@Override
	public String toString() {
		return getChallenge();
	}

	private String matchingTargets() {
		return swapLastComma("<code>" + Joiner.on("</code>, <code>").join(matchingTargets) + "</code>");
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
