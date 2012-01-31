package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

final public class PerfectMatchRegexTest {

	@Test
	public void shouldMatchAllString() {
		assertEquals(Ok.class, new PerfectMatchRegex(0, "a").judge(".").getClass());
		assertEquals(Ok.class, new PerfectMatchRegex(0, "aaabc").judge(".+").getClass());
	}

	@Test
	public void shouldNotMatchPartialString() {
		assertEquals(Failed.class, new PerfectMatchRegex(0, "aa").judge(".").getClass());
		assertEquals(Failed.class, new PerfectMatchRegex(0, "aaab\nc").judge(".+").getClass());
	}

}
