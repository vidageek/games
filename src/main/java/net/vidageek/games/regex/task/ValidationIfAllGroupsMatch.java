package net.vidageek.games.regex.task;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

final public class ValidationIfAllGroupsMatch implements GroupValidation {

	private final String[] captureGroupTargets;

	public ValidationIfAllGroupsMatch(String... captureGroupTargets) {
		this.captureGroupTargets = captureGroupTargets;

	}

	public JudgedTask judge(String challenge, Matcher matcher) {
		int i = 1;
		for (String target : captureGroupTargets) {
			if (!target.equals(matcher.group(i++))) {
				return new Failed("A regex " + challenge + " n&atilde;o captura a string " + target);
			}
		}
		return new Ok();
	}

}
