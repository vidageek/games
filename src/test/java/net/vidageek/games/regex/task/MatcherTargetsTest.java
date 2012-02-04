package net.vidageek.games.regex.task;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class MatcherTargetsTest {

	@Test
	public void shouldShowMessageWithTargets() {
		assertThat(new MatcherTargets("a", "b").showMessages(), equalTo("a e b"));
	}
	
}
