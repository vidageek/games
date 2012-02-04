package net.vidageek.games.task.status;

import net.vidageek.games.task.JudgedTask;

final public class Failed implements JudgedTask {

	private final String reason;

	public Failed(final String reason) {
		this.reason = reason;
	}

	public Failed(Faileds fails) {
		this.reason = fails.getReason();
	}

	public boolean ok() {
		return false;
	}

	public String getReason() {
		return reason;
	}
	
	@Override
	public String toString() {
		return this.reason;
	}

}
