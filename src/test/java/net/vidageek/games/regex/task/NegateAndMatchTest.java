package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;

import org.junit.Before;
import org.junit.Test;

public class NegateAndMatchTest {

	private NegateAndMatch aNegateCharClassTask;
	private MatcherTargets cannotMatch;
	private MatcherTargets shouldMatch;

	@Before
	public void setup() throws Exception {
		cannotMatch = from("a", "b");
		shouldMatch = from("c", "d");
		aNegateCharClassTask = new NegateAndMatch(cannotMatch, shouldMatch);
	}

	@Test
	public void shouldReturnFailedWhenMatch() throws Exception {
		JudgedTask judge = aNegateCharClassTask.judge("[^ef]");
		assertEquals(Failed.class, judge.getClass());
		assertEquals(	"N&atilde;o deveria fazer match com \"a\"<br>N&atilde;o deveria fazer match com \"b\"",
						judge.getReason());
	}

	@Test
	public void shouldShowChallengeWithWhatShouldMatchAndWhatCannotMatch() {
		assertThat(	aNegateCharClassTask.getChallenge(),
					equalTo("Qual RegEx n&atilde;o reconhece <code>a</code> e <code>b</code> mas reconhece <code>c</code> e <code>d</code>?"));
	}
}
