package vggames.task.status;

import vggames.task.JudgedTask;

final public class Ok implements JudgedTask {

	public boolean getOk() {
		return true;
	}

	public String getReason() {
		return "ok!";
	}

}
