package net.vidageek.games.regex.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

final public class MultipleAnswersMatcherTest {

	@Test
	public void shouldMatcherWithAllAnswers() throws Exception {
		assertTrue(new MultipleAnswersMatcher(0, "a", "b").judge("[ab]").ok());
	}

	@Test
	public void cannotMatchWith1InvalidChalengeAnswer() throws Exception {
		assertFalse(new MultipleAnswersMatcher(0, "a").judge("[c]").ok());
	}

	@Test
	public void cannotMatchWith1InvalidAnd1ValidChalengeAnswer() throws Exception {
		assertFalse(new MultipleAnswersMatcher(0, "b", "c").judge("[ca]").ok());
	}
}
