package net.vidageek.games.regex;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatcher;

public class RegexTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	@Test
	public void shouldFindAGroup() {
		String matchAtGroup = "aaaa"; 
		assertThat(new Regex("b+([a]+)c+").group(1).from("bbb"+matchAtGroup+"c"), equalTo(matchAtGroup)); 
	}

	@Test
	public void shouldThrowsAIllegalArgumentExceptionWhenGroupNotMatch() {
		final String couldNotMatch = "could not match text.";
		thrown.expect(illegalArgumentExceptionWithMessage(couldNotMatch));
		String matchAtGroup = "aaaa"; 
		assertThat(new Regex("b+([a]+)c+").group(1).from("bbbc"), equalTo(matchAtGroup)); 
	}

	
	private ArgumentMatcher<IllegalArgumentException> illegalArgumentExceptionWithMessage(final String couldNotMatchMessage) {
		return new ArgumentMatcher<IllegalArgumentException>() {
			@Override
			public boolean matches(Object exception) {
				IllegalArgumentException ex = (IllegalArgumentException) exception;
				return ex.getMessage().equals(couldNotMatchMessage);
			}
		};
	}
	
}
