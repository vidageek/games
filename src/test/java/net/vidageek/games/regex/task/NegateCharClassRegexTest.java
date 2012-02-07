package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;

import org.junit.Test;

public class NegateCharClassRegexTest {

	@Test
	public void shouldReturnFailedWhenMatch() throws Exception {
		MatcherTargets cannotMatch = MatcherTargets.fromStrings("a", "b");
		MatcherTargets shouldMatch = MatcherTargets.fromStrings("c", "d");
		JudgedTask judge = new NegateCharClassRegex(cannotMatch, shouldMatch).judge("[^ef]");
		assertEquals(Failed.class, judge.getClass());
		assertEquals("Não deveria fazer match com \"a\"<br>Não deveria fazer match com \"b\"", judge.getReason());
	}
	
	@Test
	public void shouldShowWhatTargetMatchCannotMatchWhenFail() {
		MatcherTargets negateClassWontMatch = MatcherTargets.fromStrings("a", "b");
		MatcherTargets negateClassShouldMatch = MatcherTargets.fromStrings("c", "d");
		JudgedTask judge = new NegateCharClassRegex(negateClassWontMatch, negateClassShouldMatch).judge("[^abcd]");
		assertEquals(Failed.class, judge.getClass());
		assertEquals("\"[^abcd]\" não dá match em \"c\"<br>\"[^abcd]\" não dá match em \"d\"", judge.getReason());
	}
}
