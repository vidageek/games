package net.vidageek.games.regex.task;

import static org.junit.Assert.*;

import org.junit.Test;

public class JudgeRegexTest {

	@Test
	public void shouldReturnJudgedTaskWithErrorWhenInvalidRegex() {
		assertEquals(Error.class, new JudgeRegex("aIncalidRegex)").match("anyResponse").getClass());
	}

}
