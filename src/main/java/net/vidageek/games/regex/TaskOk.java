package net.vidageek.games.regex;

final public class TaskOk implements JudgedTask {

	public boolean ok() {
		return true;
	}

	public String getReason() {
		return "ok!";
	}

}
