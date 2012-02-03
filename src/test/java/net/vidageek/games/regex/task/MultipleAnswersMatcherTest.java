package net.vidageek.games.regex.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import net.vidageek.games.task.CharClassRegex;

import org.junit.Before;
import org.junit.Test;

final public class MultipleAnswersMatcherTest {

	private CharClassRegex taskWithMatchinTargestAAndB;

	@Before
	public void setup() throws Exception {
		taskWithMatchinTargestAAndB = new CharClassRegex("A", "B");
	}
	
	@Test
	public void shouldMatcherWithAllAnswers() {
		assertEquals(Ok.class, taskWithMatchinTargestAAndB.judge("[AB]").getClass());
	}

	@Test
	public void cannotMatchWith1InvalidChalengeAnswer() {
		assertEquals(Failed.class, taskWithMatchinTargestAAndB.judge("[C]").getClass());
	}

	@Test
	public void cannotMatchWith1InvalidAnd1ValidChalengeAnswer() {
		assertEquals(Failed.class, taskWithMatchinTargestAAndB.judge("[AC]").getClass());
	}
	
	@Test
	public void shouldShowCorrectChallenge() {
		assertThat(taskWithMatchinTargestAAndB.getChallenge(), equalTo("Qual regex dá match em [A e B]"));
	}
}
