package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.junit.Test;

public class MatcherTargetsTest {

	@Test
	public void shouldInterateOverMatcherTargets() {
		assertThat(from("a", "b"), hasItems("a", "b"));
	}

	@Test
	public void shouldShowLiterallySpaceCharacters() throws Exception {
		assertThat(from("\t", "\n", "\f", "\r").showMessages(), equalTo("\"\\t\" e \"\\n\" e \"\\f\" e \"\\r\"")) ;
	}

	@Test
	public void shouldShowLiterallySpaceCharactersAtString() throws Exception {
		assertThat(from("a\te", "b\nf", "c\fg", "d\rh").showMessages(), equalTo("\"a\\te\" e \"b\\nf\" e \"c\\fg\" e \"d\\rh\"")) ;
	}

	@Test
	public void cannotScapeSpaceString() throws Exception {
		assertThat(from(" ").showMessages(), equalTo("\" \"")) ;
	}

	@Test
	public void cannotScapeSpaceStringAndAChar() throws Exception {
		assertThat(from(" a").showMessages(), equalTo("\" a\"")) ;
	}

	public void shouldBuildGoodHtmlFor1Target() {
		assertThat(from("A").asHtml(), equalTo("<code>A</code>"));
	}

	@Test
	public void shouldBuildGoodHtmlFor2Targets() {
		assertThat(from("A", "B").asHtml(), equalTo("<code>A</code> e <code>B</code>"));
	}

	@Test
	public void shouldBuildGoodHtmlFor3Targets() {
		assertThat(from("a", "b", "c").asHtml(), equalTo("<code>a</code>, <code>b</code> e <code>c</code>"));
	}

}
