package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Faileds;

public class NegateCharClassRegex implements Task {

	private final MatcherTargets cannotMatch;
	private final MatcherTargets shouldMatch;


	public NegateCharClassRegex(MatcherTargets cannotMatch, MatcherTargets shouldMatch) {
		this.cannotMatch = cannotMatch;
		this.shouldMatch = shouldMatch;
	}

	public JudgedTask judge(String challenge) {
		Faileds faileds = findUndueMatchWith(challenge);
		faileds.addAll(findDueMatchThatNotMatch(challenge));
		return faileds.judgment();
	}

	private Faileds findDueMatchThatNotMatch(String challenge) {
		return new Regex(challenge).matchAll(shouldMatch);
	}

	private Faileds findUndueMatchWith(String challenge) {
		Faileds faileds = new Faileds();
		Regex regex = new Regex(challenge);
		for (String matchingTarget : cannotMatch) {
			if (regex.match(matchingTarget).ok()) {
				faileds.addOnlyJudgedFailed(new Failed("Não deveria fazer match com \"" + matchingTarget + "\""));
			}
		}
		return faileds;
	}
	
	public String getDescription() {
		return "Matching com classes negadas de caracteres";
	}

	public String getChallenge() {
		return toString();
	}

	@Override
	public String toString() {
		return cannotMatchChallenge() + "<br>" + shouldMatchChallenge();
	}

	private String cannotMatchChallenge() {
		return "Não pode dar match em [" + cannotMatch.showMessages() + "]";
	}

	private String shouldMatchChallenge() {
		return  "Deve dar match em [" + shouldMatch.showMessages() + "]";
	}
	
}
