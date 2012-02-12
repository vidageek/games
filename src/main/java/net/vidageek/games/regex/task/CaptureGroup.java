package net.vidageek.games.regex.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;
import net.vidageek.games.task.status.Error;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

import com.google.common.base.Joiner;

final public class CaptureGroup implements Task {

	private final String matchingTarget;
	private final String[] captureGroupTargets;

	public CaptureGroup(final String matchingTarget, final String... captureGroupTargets) {
		this.matchingTarget = matchingTarget;
		this.captureGroupTargets = captureGroupTargets;
	}

	public JudgedTask judge(final String challenge) {
		try {
			Matcher matcher = Pattern.compile(challenge).matcher(matchingTarget);
			if (!matcher.find()) {
				return new Failed("A regex " + challenge + " n&atilde;o d&aacute; match em " + matchingTarget);
			}
			if (matcher.groupCount() != captureGroupTargets.length) {
				return new Failed("A regex " + challenge + " n&atilde;o cont&eacute;m " + captureGroupTargets.length
						+ " grupo(s) de captura.");
			}
			if (!matchingTarget.equals(matcher.group(0))) {
				return new Failed("A regex " + challenge + " n&atilde;o reconhece a string " + matchingTarget);
			}
			int i = 1;
			for (String target : captureGroupTargets) {
				if (!target.equals(matcher.group(i++))) {
					return new Failed("A regex " + challenge + " n&atilde;o captura a string " + target);
				}
			}
			return new Ok();
		} catch (Exception e) {
			return new Error(e);
		}
	}

	public String getChallenge() {
		return "Qual regex d&aacute; match em " + matchingTarget + " e captura " + captureTarget() + "?";
	}

	private String captureTarget() {
		return Joiner.on(", ").join(captureGroupTargets);
	}

	@Override
	public String toString() {
		return "Capturar " + captureTarget() + " no string " + matchingTarget;
	}

}
