package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;

import org.junit.Test;

public class NegateCharClassRegexTest {

	@Test
	public void shouldReturnFailedWhenMatch() throws Exception {
		JudgedTask judge = new NegateCharClassRegex("a", "b").judge("[ˆab]");
		assertEquals(Failed.class, judge.getClass());
		assertEquals("Não deveria fazer match com [a]<br>Não deveria fazer match com [b]", judge.getReason());
	}
	
}
