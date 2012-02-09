package net.vidageek.games.task.status;

import net.vidageek.games.task.JudgedTask;

final public class Error implements JudgedTask {

	private final Throwable e;

	public Error(final Throwable e) {
		this.e = e;
	}

	public boolean ok() {
		return false;
	}

	public String getReason() {
		return e.getMessage();
	}
}
