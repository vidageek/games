package net.vidageek.games.regex.task;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

final public class ValidationIfMatch implements GroupValidation {

	private final String matchingTarget;

	public ValidationIfMatch(String matchingTarget) {
		this.matchingTarget = matchingTarget;
	}
	
	public JudgedTask judge(String challenge, Matcher matcher) {
		if (!matcher.find()) {
			return new Failed("A regex " + challenge + " n&atilde;o d&aacute; match em " + matchingTarget);
		}
		return new Ok();
	}

}
