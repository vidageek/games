package net.vidageek.games.regex.task;

import static java.util.Arrays.copyOfRange;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Faileds;
import net.vidageek.games.task.status.Ok;

public class NegateCharClassRegex implements Task {

	private final MatcherTargets matchingTargets;

	public NegateCharClassRegex(String...matchingTargets) {
		this.matchingTargets = new MatcherTargets(matchingTargets[0], copyOfRange(matchingTargets, 1, matchingTargets.length));
	}

	public JudgedTask judge(String challenge) {
		Faileds faileds = findUndueMatchWith(challenge);
		return faileds.ok() ? new Ok() : new Failed(faileds);
	}

	private Faileds findUndueMatchWith(String challenge) {
		Faileds faileds = new Faileds();
		Regex regex = new Regex(challenge);
		for (String matchingTarget : matchingTargets) {
			if (regex.match(matchingTarget).ok()) {
				faileds.addOnlyJudgedFailed(new Failed("Não deveria fazer match com [" + matchingTarget + "]"));
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
		return "Não pode dar match em [" + matchingTargets.showMessages() + "]";
	}
	
}
