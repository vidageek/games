package net.vidageek.games.regex.task;

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
		String reason = "Falhou porqu&ecirc; exce&ccedil;&atilde;o foi lan&ccedil;ada:" + "<ul class=\"exception\">\n";
		reason += "<li class=\"first\">" + e.getClass().getName() + "</li>\n";
		for (StackTraceElement frame : e.getStackTrace()) {
			reason += "<li>at&nbsp;" + frame + "</li>\n";
		}
		reason += "</ul>\n";
		System.out.println(reason);
		return reason;
	}
}
