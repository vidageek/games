package net.vidageek.games.regex.task;

import static org.junit.Assert.*;

import org.junit.Test;

public class JudgeRegexTest {

	@Test
	public void shouldReturnJudgedFailedWhenSomeChalllegMatch() {
		assertEquals(Failed.class, new JudgeRegex("\\w").cannotMatchAny("a").getClass());
	}
}
