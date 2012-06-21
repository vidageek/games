package vggames.shared.task;

import vggames.regex.task.MatcherTargets;
import vggames.regex.task.Regex;

public class Match implements Task {

	private final MatcherTargets matchingTargets;

	public Match(final MatcherTargets matchingTargets) {
		this.matchingTargets = matchingTargets;
	}

	public JudgedTask judge(final String challenge) {
		return new Regex(challenge).matchAll(matchingTargets).judgment();
	}

	public String getChallenge() {
		return "Qual RegEx reconhece " + matchingTargets.asHtml() + "?";
	}

	@Override
	public String toString() {
		return getChallenge();
	}
}
