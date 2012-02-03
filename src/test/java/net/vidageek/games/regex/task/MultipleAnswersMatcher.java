package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

import com.google.common.base.Joiner;

public class MultipleAnswersMatcher implements Task {

	private final int index;
	private final String[] matchingTargets;

	public MultipleAnswersMatcher(String... matchingTargets) {
		this.index = 0;
		this.matchingTargets = matchingTargets;

	}

	private MultipleAnswersMatcher(int index, String... matchingTargets) {
		this.index = index;
		this.matchingTargets = matchingTargets;
	}

	public JudgedTask judge(String challenge) {
		return new JudgeRegex(challenge).matchAll(this.matchingTargets);
	}

	public String getDescription() {
		return "Matching com grupos de caracteres";
	}

	public String getChallenge() {
		return "Qual regex dá match em [" + Joiner.on(" e ").join(this.matchingTargets) + "]";
	}

	public int getIndex() {
		return this.index;
	}

	@Override
	public String toString() {
		return "Match em " + Joiner.on(" e ").join(this.matchingTargets);
	}

	public Task withIndex(final int index) {
		return new MultipleAnswersMatcher(index, this.matchingTargets);
	}
}
