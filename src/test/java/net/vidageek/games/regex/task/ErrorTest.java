package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.status.Error;

import org.junit.Test;

final public class ErrorTest {

	@Test
	public void stacktraceShouldBePrintedInAnHtmlListWithClassException() {
		final String reason = new Error(new Throwable("Message :D")).getReason();
		assertEquals("Message :D", reason);
	}
}
