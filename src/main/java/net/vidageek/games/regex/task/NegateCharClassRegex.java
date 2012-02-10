package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Faileds;

public class NegateCharClassRegex implements Task {

	private final MatcherTargets cannotMatch;
	private final MatcherTargets shouldMatch;

	public NegateCharClassRegex(final MatcherTargets cannotMatch, final MatcherTargets shouldMatch) {
		this.cannotMatch = cannotMatch;
		this.shouldMatch = shouldMatch;
	}

	public JudgedTask judge(final String challenge) {
		Faileds faileds = findUndueMatchWith(challenge);
		faileds.addAll(findDueMatchThatNotMatch(challenge));
		return faileds.judgment();
	}

	private Faileds findDueMatchThatNotMatch(final String challenge) {
		return new Regex(challenge).matchAll(shouldMatch);
	}

	private Faileds findUndueMatchWith(final String challenge) {
		Faileds faileds = new Faileds();
		Regex regex = new Regex(challenge);
		for (String matchingTarget : cannotMatch) {
			if (regex.match(matchingTarget).getOk()) {
				faileds.addOnlyJudgedFailed(new Failed("N&atilde;o deveria fazer match com \"" + matchingTarget + "\""));
			}
		}
		return faileds;
	}

	public String getChallenge() {
		return toString();
	}

	@Override
	public String toString() {
		return cannotMatchChallenge() + " e " + shouldMatchChallenge();
	}

	private String cannotMatchChallenge() {
		return "N&atilde;o pode dar match em " + cannotMatch.showMessages();
	}

	private String shouldMatchChallenge() {
		return "Deve dar match em " + shouldMatch.showMessages();
	}

}
