package net.vidageek.games.regex.task;

import net.vidageek.games.regex.Regex;
import net.vidageek.games.task.JudgedTask;

public class JudgeRegex {

	private final String challenge;

	public JudgeRegex(String challenge) {
		this.challenge = challenge;
	}

	public JudgedTask match(String matchingTarget) {
		return matchAll(matchingTarget);

	}

	public JudgedTask matchAll(String... matchingTargets) {
		try {
			return new Regex(challenge).matchAll(matchingTargets);
		} catch (Exception e) {
			return new Error(e);
		}
	}

	public JudgedTask matchAny(String... matchingTargets) {
		try {
			Faileds faileds = findUndue(matchingTargets);
			return faileds.ok()? new Ok() : new Failed(faileds);
		} catch (Exception e) {
			return new Error(e);
		}
	}

	private Faileds findUndue(String... matchingTargets) {
		Faileds faileds = new Faileds();
		Regex regex = new Regex(challenge);
		for (String matchingTarget : matchingTargets) {
			if(regex.match(matchingTarget).ok()) {
				faileds.addOnlyJudgedFailed(new Failed("Não deveria fazer match com ["+matchingTarget+"]"));
			}
		}
		return faileds;
	}

}
