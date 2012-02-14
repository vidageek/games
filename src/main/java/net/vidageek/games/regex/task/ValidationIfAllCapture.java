package net.vidageek.games.regex.task;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

public class ValidationIfAllCapture implements GroupValidation {

	private final String[] captureGroupTargets;

	public ValidationIfAllCapture(String...captureGroupTargets) {
		this.captureGroupTargets = captureGroupTargets;
	}
	
	public JudgedTask judge(String challenge, Matcher matcher) {
		if (matcher.groupCount() != captureGroupTargets.length) {
			return new Failed("A regex " + challenge + " n&atilde;o cont&eacute;m " + captureGroupTargets.length
					+ " grupo(s) de captura.");
		}
		return new Ok();
	}

}
