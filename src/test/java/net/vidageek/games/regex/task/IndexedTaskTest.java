package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import net.vidageek.games.regex.task.NegateCharClassRegex;
import net.vidageek.games.regex.task.PerfectMatchRegex;
import net.vidageek.games.task.IndexedTask;
import net.vidageek.games.task.status.Error;

import org.junit.Test;

public class IndexedTaskTest {

	@Test
	public void shouldReturnJudgedTaskWithErrorWhenInvalidRegex() {
		assertEquals(Error.class, new IndexedTask(new PerfectMatchRegex("a"), 1).judge("aIncalidRegex)").getClass());
	}

	@Test
	public void shouldReturnJudgedTaskWithErrorWhenCannotMatchAny() {
		assertEquals(Error.class, new IndexedTask(new NegateCharClassRegex("aAnswer"), 1).judge("aIncalidRegex)").getClass());
	}

}
