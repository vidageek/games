package net.vidageek.games.regex.task;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

final public class ErrorTest {

	@Test
	public void stacktraceShouldBePrintedInAnHtmlListWithClassException() {
		final String reason = new Error(new Throwable()).getReason();
		assertTrue(reason.contains("<ul class=\"exception\">"));
		assertTrue(reason.contains("Falhou porque exce&ccedil;&atilde;o foi lan&ccedil;ada:"));
		assertTrue(reason.contains("<li class=\"first\">java.lang.Throwable</li>"));
		assertTrue(reason.contains("<li>at&nbsp;net.vidageek.games.regex.task.ErrorTest."
				+ "stacktraceShouldBePrintedInAnHtmlListWithClassException(ErrorTest.java"));
		assertTrue(reason.contains("</ul>"));
	}
}
