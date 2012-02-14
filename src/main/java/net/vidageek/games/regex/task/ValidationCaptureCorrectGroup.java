package net.vidageek.games.regex.task;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

final public class ValidationCaptureCorrectGroup implements GroupValidation {

	private final String matchingTarget;

	public ValidationCaptureCorrectGroup(String matchingTarget) {
		this.matchingTarget = matchingTarget;
	}
	
	public JudgedTask judge(String challenge, Matcher matcher) {
		if (!matchingTarget.equals(matcher.group(0))) {
			return new Failed("A regex " + challenge + " n&atilde;o reconhece a string " + matchingTarget);
		}
		return new Ok();
	}

}
