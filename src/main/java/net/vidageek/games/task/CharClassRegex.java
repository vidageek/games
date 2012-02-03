package net.vidageek.games.task;

import net.vidageek.games.regex.task.JudgeRegex;

import com.google.common.base.Joiner;

public class CharClassRegex implements Task {

	private final String[] matchingTargets;

	public CharClassRegex(String... matchingTargets) {
		this.matchingTargets = matchingTargets;

	}

	public JudgedTask judge(String challenge) {
		return new JudgeRegex(challenge).matchAll(this.matchingTargets);
	}

	public String getDescription() {
		return "Matching com classes de caracteres";
	}

	public String getChallenge() {
		return "Qual regex dá match em [" + matchingTargets() + "]";
	}

	@Override
	public String toString() {
		return "Match em " + matchingTargets();
	}

	private String matchingTargets() {
		return Joiner.on(" e ").join(this.matchingTargets);
	}
	
	
}
