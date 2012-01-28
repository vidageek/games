package net.vidageek.games.regex;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

final public class TaskError implements JudgedTask {

	private final Throwable e;

	public TaskError(final Throwable e) {
		this.e = e;
	}

	public boolean ok() {
		return false;
	}

	public String getReason() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(baos));
		return "failed because exception was raised: " + new String(baos.toByteArray());
	}

}
