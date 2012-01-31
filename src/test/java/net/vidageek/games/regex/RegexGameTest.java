package net.vidageek.games.regex;

import net.vidageek.games.regex.test.infra.Person;

import org.junit.Test;

final public class RegexGameTest {

	@Test
	public void allTasksMustHaveAnswers() {
		new Person("a", "b", "ab", "abc", "\\\\", "\\$", "abcdefg", "ab\\$cd\\^ef\\\\g").play(new RegexGame());
	}

}
