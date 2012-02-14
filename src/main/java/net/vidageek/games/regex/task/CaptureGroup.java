package net.vidageek.games.regex.task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;
import net.vidageek.games.task.status.Ok;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

final public class CaptureGroup implements Task {

	private final String matchingTarget;
	private final String[] captureGroupTargets;
	private final List<GroupValidation> validations = Lists.newArrayList();

	public CaptureGroup(final String matchingTarget, final String... captureGroupTargets) {
		this.matchingTarget = matchingTarget;
		this.captureGroupTargets = captureGroupTargets;
		addAllValidations();
	}

	private void addAllValidations() {
		validations.add(new ValidationIfMatch(this.matchingTarget));
		validations.add(new ValidationIfAllCapture(this.captureGroupTargets));
		validations.add(new ValidationCaptureCorrectGroup(this.matchingTarget));
		validations.add(new ValidationIfAllGroupsMatch(this.captureGroupTargets));
	}

	public JudgedTask judge(final String challenge) {
		final Matcher matcher = Pattern.compile(challenge).matcher(matchingTarget);
		return applyAllValidations(challenge, matcher);

	}

	private JudgedTask applyAllValidations(final String challenge, final Matcher matcher) {
		JudgedTask judge = new Ok();
		for (GroupValidation validation : validations) {
			judge = validation.judge(challenge, matcher);
			if (!judge.getOk()) {
				return judge;
			}
		}
		return judge;
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
