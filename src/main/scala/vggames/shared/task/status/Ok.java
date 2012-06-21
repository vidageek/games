package vggames.shared.task.status;

import vggames.shared.task.JudgedTask;

final public class Ok implements JudgedTask {

	public boolean getOk() {
		return true;
	}

	public String getReason() {
		return "ok!";
	}

}
