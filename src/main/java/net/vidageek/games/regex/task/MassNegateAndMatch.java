package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;

import java.io.InputStream;
import java.util.Scanner;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

public class MassNegateAndMatch implements Task {

	private final String challenge;
	private final NegateAndMatch matcher;

	public MassNegateAndMatch(final String fileName, final String challenge) {
		this.challenge = challenge;
		InputStream stream = MassNegateAndMatch.class.getResourceAsStream("/mass/" + fileName + ".match");
		String[] mustMatch = new String[] {};
		if (stream != null) {
			mustMatch = new Scanner(stream).useDelimiter("$$").next().split("\n");
		}
		stream = MassNegateAndMatch.class.getResourceAsStream("/mass/" + fileName + ".not");
		String[] cantMatch = new String[] {};
		if (stream != null) {
			cantMatch = new Scanner(stream).useDelimiter("$$").next().split("\n");
		}
		matcher = new NegateAndMatch(from(cantMatch), from(mustMatch));
	}

	public JudgedTask judge(final String challenge) {
		return matcher.judge(challenge);
	}

	public String getChallenge() {
		return challenge;
	}

}
