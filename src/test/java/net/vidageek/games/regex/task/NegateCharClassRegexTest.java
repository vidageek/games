package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.fromStrings;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.status.Failed;

import org.junit.Before;
import org.junit.Test;

public class NegateCharClassRegexTest {

	private NegateCharClassRegex aNegateCharClassTask;
	private MatcherTargets cannotMatch;
	private MatcherTargets shouldMatch;

	@Before
	public void setup() throws Exception {
		cannotMatch = fromStrings("a", "b");
		shouldMatch = fromStrings("c", "d");
		aNegateCharClassTask = new NegateCharClassRegex(cannotMatch, shouldMatch);
	}
	
	@Test
	public void shouldReturnFailedWhenMatch() throws Exception {
		JudgedTask judge = aNegateCharClassTask.judge("[^ef]");
		assertEquals(Failed.class, judge.getClass());
		assertEquals("Não deveria fazer match com \"a\"<br>Não deveria fazer match com \"b\"", judge.getReason());
	}
	
	@Test
	public void shouldShowChallengeWithWhatShouldMatchAndWhatCannotMatch() {
		String completeMessage =  whatCannotMatch() + " e " + whatShouldMatch();  
		assertThat(aNegateCharClassTask.getChallenge(), equalTo(completeMessage));
	}

	private String whatShouldMatch() {
		return "Deve dar match em " + shouldMatch.showMessages();
	}

	private String whatCannotMatch() {
		return "Não pode dar match em " + cannotMatch.showMessages();
	}
}
