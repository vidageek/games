package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

final public class PerfectMatchRegexTest {

	@Test
	public void shouldMatchAllString() {
		assertEquals(Ok.class, new PerfectMatchRegex("a", 0).judge(".").getClass());
		assertEquals(Ok.class, new PerfectMatchRegex("aaabc", 0).judge(".+").getClass());
	}

	@Test
	public void shouldNotMatchPartialString() {
		assertEquals(Failed.class, new PerfectMatchRegex("aa", 0).judge(".").getClass());
		assertEquals(Failed.class, new PerfectMatchRegex("aaab\nc", 0).judge(".+").getClass());
	}

}
