package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

/**
 * 
 * @author juliano alves
 * 
 */
final public class OperatorMatcher implements Task {

	private final String matchingTarget;

	public OperatorMatcher(final String matchingTarget) {
		this.matchingTarget = matchingTarget;
	}

	public JudgedTask judge(final String challenge) {
		return new Regex(challenge).match(matchingTarget);
	}

	public String getDescription() {
		return null;
	}

	public String getChallenge() {
		return null;
	}
}
