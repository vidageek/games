package net.vidageek.games.regex;

import com.google.common.base.Joiner;

import net.vidageek.games.regex.task.JudgeRegex;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

public class NegateCharClassRegex implements Task {

	private final String[] matchingTargets;

	public NegateCharClassRegex(String...matchingTargets) {
		this.matchingTargets = matchingTargets;
	}

	public JudgedTask judge(String challenge) {
		return new JudgeRegex(challenge).cannotMatchAny(matchingTargets);
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
