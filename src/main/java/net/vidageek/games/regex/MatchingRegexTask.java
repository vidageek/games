package net.vidageek.games.regex;

import java.util.regex.Pattern;

final public class MatchingRegexTask implements Task {

	private final int index;
	private final String matchingTarget;

	public MatchingRegexTask(final String matchingTarget, final int index) {
		this.matchingTarget = matchingTarget;
		this.index = index;
	}

	public JudgedTask judge(final String challenge) {
		try {
			if (Pattern.compile(challenge).matcher(matchingTarget).matches()) {
				return new TaskOk();
			}
			return new TaskFail("[" + challenge + "] não dá match em [" + matchingTarget + "]");
		} catch (Exception e) {
			return new TaskError(e);
		}
	}

	public String getDescription() {
		return "Matching simples de letras";
	}

	public String getChallenge() {
		return "Qual regex dá match em [" + matchingTarget + "]?";
	}

	public int getIndex() {
		return index;
	}

}
