package vggames.regex.task;

import vggames.task.JudgedTask;
import vggames.task.Task;
import vggames.task.status.Faileds;

public class NegateAndMatch implements Task {

	private final MatcherTargets cannotMatch;
	private final MatcherTargets shouldMatch;

	public NegateAndMatch(final MatcherTargets cannotMatch,
			final MatcherTargets shouldMatch) {
		this.cannotMatch = cannotMatch;
		this.shouldMatch = shouldMatch;
	}

	public JudgedTask judge(final String challenge) {
		Faileds faileds = new Regex(challenge).matchNone(cannotMatch);
		faileds.addAll(new Regex(challenge).matchAll(shouldMatch));
		return faileds.judgment();
	}

	public String getChallenge() {
		return "Qual RegEx n&atilde;o reconhece " + cannotMatch.asHtml()
				+ " mas reconhece " + shouldMatch.asHtml() + "?";
	}

	@Override
	public String toString() {
		return getChallenge();
	}

}
