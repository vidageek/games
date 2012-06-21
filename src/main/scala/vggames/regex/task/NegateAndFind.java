package vggames.regex.task;

import vggames.shared.task.JudgedTask;
import vggames.shared.task.Task;
import vggames.shared.task.status.Faileds;

public class NegateAndFind implements Task {

	private final MatcherTargets mustMatch;
	private final MatcherTargets cannotMatch;

	public NegateAndFind(final MatcherTargets cannotMatch,
			final MatcherTargets mustMatch) {
		this.cannotMatch = cannotMatch;
		this.mustMatch = mustMatch;
	}

	public JudgedTask judge(final String challenge) {
		Faileds faileds = new Regex(challenge).findNone(cannotMatch);
		faileds.addAll(new Regex(challenge).matchAll(mustMatch));
		return faileds.judgment();
	}

	public String getChallenge() {
		return "Qual RegEx n&atilde;o reconhece parcialmente"
				+ cannotMatch.asHtml() + " mas reconhece " + mustMatch.asHtml()
				+ "?";
	}

	@Override
	public String toString() {
		return getChallenge();
	}

}
