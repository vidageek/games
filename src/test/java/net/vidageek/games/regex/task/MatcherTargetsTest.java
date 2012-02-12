package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.fromStrings;
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
	public void shouldShowLiterallySpaceCharacters() throws Exception {
		assertThat(fromStrings("\t", "\n", "\f", "\r").showMessages(), equalTo("\"\\t\" e \"\\n\" e \"\\f\" e \"\\r\"")) ;
	}

	@Test
	public void shouldShowLiterallySpaceCharactersAtString() throws Exception {
		assertThat(fromStrings("a\te", "b\nf", "c\fg", "d\rh").showMessages(), equalTo("\"a\\te\" e \"b\\nf\" e \"c\\fg\" e \"d\\rh\"")) ;
	}

	@Test
	public void cannotScapeSpaceString() throws Exception {
		assertThat(fromStrings(" ").showMessages(), equalTo("\" \"")) ;
	}

	@Test
	public void cannotScapeSpaceStringAndAChar() throws Exception {
		assertThat(fromStrings(" a").showMessages(), equalTo("\" a\"")) ;
	}

	@Test
	public void shouldInterateOverMatcherTargets() {
		assertThat(aMatcherTargetWithAAndB(), hasItems("a", "b"));
	}

	private MatcherTargets aMatcherTargetWithAAndB() {
		return fromStrings("a", "b");
	}

}
