package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;

final public class Ok implements JudgedTask {

	public boolean ok() {
		return true;
	}

	public String getReason() {
		return "ok!";
	}

}
