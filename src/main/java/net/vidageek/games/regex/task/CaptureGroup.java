package net.vidageek.games.regex.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

final public class CaptureGroup implements Task {

	private final int index;
	private final String matchingTarget;
	private final String[] groupMatchingTargets;

	public CaptureGroup(final String matchingTarget, final String... groupMatchingTargets) {
		this.matchingTarget = matchingTarget;
		this.index = 0;
		this.groupMatchingTargets = groupMatchingTargets;
	}

	private CaptureGroup(int index, String matchingTarget, String... groupMatchingTargets) {
		this.index = index;
		this.matchingTarget = matchingTarget;
		this.groupMatchingTargets = groupMatchingTargets;
	}

	public JudgedTask judge(final String challenge) {
		try {
			Matcher matcher = Pattern.compile(challenge).matcher(matchingTarget);
			if (!matcher.find()) {
				return new Failed("A regex " + challenge + " não dá match em " + matchingTarget);
			}
			if (matcher.groupCount() != groupMatchingTargets.length) {
				return new Failed("A regex " + challenge + " não contém um grupo de captura.");
			}
			if (!matchingTarget.equals(matcher.group(0))) {
				return new Failed("A regex " + challenge + " não reconhece a string " + matchingTarget);
			}
			int i = 1;
			for (String target : groupMatchingTargets) {
				if (!target.equals(matcher.group(i++))) {
					return new Failed("A regex " + challenge + " não captura a string " + target);
				}
			}
			return new Ok();
		} catch (Exception e) {
			return new Error(e);
		}
	}

	public String getDescription() {
		return "Único grupo de captura";
	}

	public String getChallenge() {
		return "Qual regex dá match em " + matchingTarget + " e captura " + captureTarget() + "?";
	}

	private String captureTarget() {
		String result = "";
		for (String target : groupMatchingTargets) {
			result += target + ", ";
		}
		return result.substring(0, result.length() - 2);
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "Capturar " + captureTarget() + " no string " + matchingTarget;
	}

	public Task withIndex(final int index) {
		return new CaptureGroup(index, matchingTarget, this.groupMatchingTargets);
	}

}
