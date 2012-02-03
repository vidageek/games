package net.vidageek.games.regex;

import net.vidageek.games.regex.test.infra.Person;

import org.junit.Test;

final public class RegexGameTest {

	@Test
	public void allTasksMustHaveAnswers() {
		new Person(
				// TODO: Review this, strange control index
				"a", "b", "ab", "abc", "\\\\", "\\$", "abcdefg", "ab\\$cd\\^ef\\\\g"
				, "[ab]", "[ab]d", "[abc]d", "[a-c]", "[a-cA-D]", "[0-2]", "\\d", "\\da", "[\\da]", "\\s", "\\sa", "[\\sa]", "\\w", "\\wp", "."
				, "[^ab]", "[^ab]d", "[^abc]d", "\\D", "\\Da", "[^\\da]", "\\S", "\\Sa", "[^\\sa]", "\\W", "\\Wp"
				, "a?"
				, "([a-z]+)", "([a-z]+).*", "([a-z]+)(.*)", "(([a-z]+)(.*)a)")
				.play(new RegexGame());
	}

}
