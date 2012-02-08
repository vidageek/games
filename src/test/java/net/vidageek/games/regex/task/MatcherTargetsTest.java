package net.vidageek.games.regex.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.junit.Test;

public class MatcherTargetsTest {

	@Test
	public void shouldShowMessageWithTargets() {
		assertThat(aMatcherTargetWithAAndB().showMessages(), equalTo("\"a\" e \"b\""));
	}

	@Test
	public void shouldInterateOverMatcherTargets() {
		assertThat(aMatcherTargetWithAAndB(), hasItems("a", "b"));
	}

	private MatcherTargets aMatcherTargetWithAAndB() {
		return MatcherTargets.fromStrings("a", "b");
	}

}
