package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

final public class PerfectMatchRegex implements Task {

	private final int index;
	private final String matchingTarget;

	public PerfectMatchRegex(final String matchingTarget) {
		this.index = 0;
		this.matchingTarget = matchingTarget;
	}

	private PerfectMatchRegex(final int index, final String matchingTarget) {
		this.index = index;
		this.matchingTarget = matchingTarget;
	}

	public JudgedTask judge(final String challenge) {
		return new JudgeRegex(challenge).match(matchingTarget);
	}

	public String getDescription() {
		return "Matching simples de letras";
	}

	public String getChallenge() {
		return "Qual regex dá match em [" + matchingTarget + "]?";
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "Match em " + matchingTarget;
	}

	public Task withIndex(final int index) {
		return new PerfectMatchRegex(index, this.matchingTarget);
	}

}
