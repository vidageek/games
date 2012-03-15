package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

public class NegateAndFind implements Task {

	private final MatcherTargets mustMatch;
	private final MatcherTargets cannotMatch;

	public NegateAndFind(MatcherTargets cannotMatch, MatcherTargets mustMatch) {
		this.cannotMatch = cannotMatch;
		this.mustMatch = mustMatch;
	}

	public JudgedTask judge(String challenge) {
		return null;
	}

	public String getChallenge() {
		return "Qual RegEx n&atilde;o reconhece parcialmente"
				+ cannotMatch.asHtml() + " mas reconhece " + mustMatch.asHtml()
				+ "?";
	}

	@Override
	public String toString() {
		return getChallenge();
	}

}
