package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

final public class SimpleMatch implements Task {

	private final String matchingTarget;

	public SimpleMatch(final String matchingTarget) {
		this.matchingTarget = matchingTarget;
	}

	public JudgedTask judge(final String challenge) {
		return new Regex(challenge).match(matchingTarget);
	}

	public String getChallenge() {
		return "Qual regex d&aacute; reconhece <code>" + matchingTarget + "</code>?";
	}

	@Override
	public String toString() {
		return getChallenge();
	}
}
