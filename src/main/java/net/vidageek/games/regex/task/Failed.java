package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;

final public class Failed implements JudgedTask {

	private final String reason;

	public Failed(final String reason) {
		this.reason = reason;
	}

	public boolean ok() {
		return false;
	}

	public String getReason() {
		return reason;
	}

}
