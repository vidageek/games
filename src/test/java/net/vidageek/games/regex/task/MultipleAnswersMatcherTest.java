package net.vidageek.games.regex.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

final public class MultipleAnswersMatcherTest {

	private MultipleAnswersMatcher taskWithMatchinTargestAAndB;
	private int taksIndex;

	@Before
	public void setup() throws Exception {
		taksIndex = 10;
		taskWithMatchinTargestAAndB = new MultipleAnswersMatcher(taksIndex, "A", "B");
	}
	
	@Test
	public void shouldMatcherWithAllAnswers() throws Exception {
		assertTrue(taskWithMatchinTargestAAndB.judge("[AB]").ok());
	}

	@Test
	public void cannotMatchWith1InvalidChalengeAnswer() throws Exception {
		assertFalse(taskWithMatchinTargestAAndB.judge("[C]").ok());
	}

	@Test
	public void cannotMatchWith1InvalidAnd1ValidChalengeAnswer() throws Exception {
		assertFalse(taskWithMatchinTargestAAndB.judge("[AC]").ok());
	}
	
	@Test
	public void shouldDefineCorrectIndex() throws Exception {
		assertThat(taskWithMatchinTargestAAndB.getIndex(), equalTo(taksIndex)); 
	}
}
