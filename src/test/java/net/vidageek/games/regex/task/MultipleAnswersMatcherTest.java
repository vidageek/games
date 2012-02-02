package net.vidageek.games.regex.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

final public class MultipleAnswersMatcherTest {

	private MultipleAnswersMatcher taskWithMatchinTargestAAndB;

	@Before
	public void setup() throws Exception {
		taskWithMatchinTargestAAndB = new MultipleAnswersMatcher("A", "B");
	}
	
	@Test
	public void shouldMatcherWithAllAnswers() {
		assertTrue(taskWithMatchinTargestAAndB.judge("[AB]").ok());
	}

	@Test
	public void cannotMatchWith1InvalidChalengeAnswer() {
		assertFalse(taskWithMatchinTargestAAndB.judge("[C]").ok());
	}

	@Test
	public void cannotMatchWith1InvalidAnd1ValidChalengeAnswer() {
		assertFalse(taskWithMatchinTargestAAndB.judge("[AC]").ok());
	}
	
	@Test
	public void shouldShowCorrectChallenge() {
		assertThat(taskWithMatchinTargestAAndB.getChallenge(), equalTo("Qual regex dá match em [A e B]"));
	}
}
