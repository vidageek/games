package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;

import net.vidageek.games.regex.task.SimpleMatch;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

import org.junit.Test;

final public class SimpleMatchTest {

	@Test
	public void shouldMatchAllString() {
		assertEquals(Ok.class, new SimpleMatch("a").judge(".").getClass());
		assertEquals(Ok.class, new SimpleMatch("aaabc").judge(".+").getClass());
	}

	@Test
	public void shouldNotMatchPartialString() {
		assertEquals(Failed.class, new SimpleMatch("aa").judge(".").getClass());
		assertEquals(Failed.class, new SimpleMatch("aaab\nc").judge(".+").getClass());
	}

}
