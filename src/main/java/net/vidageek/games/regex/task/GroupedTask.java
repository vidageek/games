package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;
import net.vidageek.games.task.TaskWithDescription;

final public class GroupedTask implements TaskWithDescription {

	private final TaskGroup group;
	private final Task task;

	public GroupedTask(final TaskGroup group, final Task task) {
		this.group = group;
		this.task = task;
	}

	public JudgedTask judge(final String challenge) {
		return task.judge(challenge);
	}

	public String getChallenge() {
		return task.getChallenge();
	}

	public String getDescription() {
		return group.getDescription();
	}

	@Override
	public String toString() {
		return task.toString();
	}

}
