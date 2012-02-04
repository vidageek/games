package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;

import net.vidageek.games.regex.task.PerfectMatchRegex;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

import org.junit.Test;

final public class PerfectMatchRegexTest {

	@Test
	public void shouldMatchAllString() {
		assertEquals(Ok.class, new PerfectMatchRegex("a").judge(".").getClass());
		assertEquals(Ok.class, new PerfectMatchRegex("aaabc").judge(".+").getClass());
	}

	@Test
	public void shouldNotMatchPartialString() {
		assertEquals(Failed.class, new PerfectMatchRegex("aa").judge(".").getClass());
		assertEquals(Failed.class, new PerfectMatchRegex("aaab\nc").judge(".+").getClass());
	}

}
