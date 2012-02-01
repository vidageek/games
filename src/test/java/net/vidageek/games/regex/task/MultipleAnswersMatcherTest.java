package net.vidageek.games.regex.task;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

final public class MultipleAnswersMatcherTest {

	@Test
	public void shouldMatcherWithAllAnswers() throws Exception {
		assertTrue(new MultipleAnswersMatcher(0, "a", "b").judge("[ab]").ok());
	}
	
}
