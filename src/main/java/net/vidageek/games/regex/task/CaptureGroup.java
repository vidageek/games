package net.vidageek.games.regex.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

final public class CaptureGroup implements Task {

	private final int index;
	private final String matchingTarget;
	private final String groupOneMatchingTarget;

	public CaptureGroup(final int index, final String matchingTarget, final String groupOneMatchingTarget) {
		this.matchingTarget = matchingTarget;
		this.index = index;
		this.groupOneMatchingTarget = groupOneMatchingTarget;
	}

	public JudgedTask judge(final String challenge) {
		try {
			Matcher matcher = Pattern.compile(challenge).matcher(matchingTarget);
			if (!matcher.find()) {
				return new Failed("A regex " + challenge + " não dá match em " + matchingTarget);
			}
			if (matcher.groupCount() != 1) {
				return new Failed("A regex " + challenge + " não contém um grupo de captura.");
			}
			if (!matchingTarget.equals(matcher.group(0))) {
				return new Failed("A regex " + challenge + " não reconhece a string " + matchingTarget);
			}
			if (!groupOneMatchingTarget.equals(matcher.group(1))) {
				return new Failed("A regex " + challenge + " não captura a string " + matchingTarget);
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
		return "Qual regex dá match em " + matchingTarget + " e captura " + groupOneMatchingTarget + "?";
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "Capturar " + groupOneMatchingTarget + " no string " + matchingTarget;
	}

}
