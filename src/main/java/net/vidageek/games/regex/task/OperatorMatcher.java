package net.vidageek.games.regex.task;

import net.vidageek.games.regex.Regex;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

/**
 * 
 * @author juliano alves
 * 
 */
final public class OperatorMatcher implements Task {

	private final int index;
	private final String matchingTarget;

	public OperatorMatcher(final String matchingTarget) {
		this.index = 0;
		this.matchingTarget = matchingTarget;
	}

	private OperatorMatcher(int index, String matchingTarget) {
		this.index = index;
		this.matchingTarget = matchingTarget;
	}

	public JudgedTask judge(final String challenge) {
		try {
			return new Regex(challenge).matchAll(matchingTarget);
		} catch (Exception e) {
			return new Error(e);
		}
	}

	public String getDescription() {
		return null;
	}

	public String getChallenge() {
		return null;
	}

	public int getIndex() {
		return index;
	}

	public Task withIndex(final int index) {
		return new OperatorMatcher(index, matchingTarget);
	}

}
