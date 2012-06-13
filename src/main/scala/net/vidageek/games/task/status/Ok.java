package net.vidageek.games.task.status;

import net.vidageek.games.task.JudgedTask;

final public class Ok implements JudgedTask {

	public boolean getOk() {
		return true;
	}

	public String getReason() {
		return "ok!";
	}

}
