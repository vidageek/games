package net.vidageek.games.regex;

import net.vidageek.games.regex.task.Failed;
import net.vidageek.games.regex.task.Faileds;
import net.vidageek.games.regex.task.Ok;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

import com.google.common.base.Joiner;

public class NegateCharClassRegex implements Task {

	private final String[] matchingTargets;

	public NegateCharClassRegex(String...matchingTargets) {
		this.matchingTargets = matchingTargets;
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
		return "Não pode dar match em [" + Joiner.on(" e ").join(matchingTargets) + "]";
	}
	
}
