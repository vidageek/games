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
		return new Regex(challenge).matchAll(matchingTargets);
	}

}
