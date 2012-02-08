package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;

import org.junit.Before;
import org.junit.Test;

public class NegateCharClassRegexTest {

	private NegateCharClassRegex aNegateCharClassTask;

	@Before
	public void setup() throws Exception {
		MatcherTargets cannotMatch = MatcherTargets.fromStrings("a", "b");
		MatcherTargets shouldMatch = MatcherTargets.fromStrings("c", "d");
		aNegateCharClassTask = new NegateCharClassRegex(cannotMatch, shouldMatch);
	}
	
	@Test
	public void shouldReturnFailedWhenMatch() throws Exception {
		JudgedTask judge = aNegateCharClassTask.judge("[^ef]");
		assertEquals(Failed.class, judge.getClass());
		assertEquals("Não deveria fazer match com \"a\"<br>Não deveria fazer match com \"b\"", judge.getReason());
	}
	
	@Test
	public void shouldShowWhatTargetMatchCannotMatchWhenFail() {
		JudgedTask judge = aNegateCharClassTask.judge("[^abcd]");
		assertEquals(Failed.class, judge.getClass());
		assertEquals("\"[^abcd]\" não dá match em \"c\"<br>\"[^abcd]\" não dá match em \"d\"", judge.getReason());
	}
}
