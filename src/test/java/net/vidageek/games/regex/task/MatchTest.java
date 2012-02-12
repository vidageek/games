package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.fromStrings;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import net.vidageek.games.task.Match;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

import org.junit.Before;
import org.junit.Test;

final public class MatchTest {

	private Match taskWithMatchinTargestAAndB;

	@Before
	public void setup() throws Exception {
		taskWithMatchinTargestAAndB = new Match(fromStrings("A", "B"));
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
		assertThat(	taskWithMatchinTargestAAndB.getChallenge(),
					equalTo("Qual regex reconhece <code>A</code> e <code>B</code>?"));
	}

	@Test
	public void shouldShowCorrectChallengeFor3MatchingTargets() {
		assertThat(	new Match(fromStrings("a", "b", "c")).getChallenge(),
					equalTo("Qual regex reconhece <code>a</code>, <code>b</code> e <code>c</code>?"));
	}

	@Test
	public void shouldMatchAllString() {
		assertTrue(new Match(fromStrings("a")).judge(".").getOk());
		assertTrue(new Match(fromStrings("aaabc")).judge(".+").getOk());
	}

	@Test
	public void shouldNotMatchPartialString() {
		assertFalse(new Match(fromStrings("aa")).judge(".").getOk());
		assertFalse(new Match(fromStrings("aaab\nc")).judge(".+").getOk());
	}
}
