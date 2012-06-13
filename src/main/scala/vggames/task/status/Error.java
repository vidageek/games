package vggames.task.status;

import vggames.task.JudgedTask;

final public class Error implements JudgedTask {

	private final Throwable e;

	public Error(final Throwable e) {
		this.e = e;
	}

	public boolean getOk() {
		return false;
	}

	public String getReason() {
		return e.getMessage();
	}
}
