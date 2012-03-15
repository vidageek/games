package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;

import java.util.Scanner;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

public class MassNegateAndMatch implements Task {

	private final String challenge;
	private final NegateAndMatch matcher;

	public MassNegateAndMatch(final String fileName, final String challenge) {
		this.challenge = challenge;
		String[] mustMatch = new Scanner(MassNegateAndMatch.class.getResourceAsStream("/mass/" + fileName + ".match"))
				.useDelimiter("$$").next().split("\n");
		String[] cantMatch = new Scanner(MassNegateAndMatch.class.getResourceAsStream("/mass/" + fileName + ".not"))
				.useDelimiter("$$").next().split("\n");
		matcher = new NegateAndMatch(from(cantMatch), from(mustMatch));
	}

	public JudgedTask judge(final String challenge) {
		return matcher.judge(challenge);
	}

	public String getChallenge() {
		return challenge;
	}

}
