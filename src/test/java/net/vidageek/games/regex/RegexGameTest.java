package net.vidageek.games.regex;

import net.vidageek.games.regex.test.infra.Player;

import org.junit.Test;

final public class RegexGameTest {

	@Test
	public void allTasksMustHaveAnswers() {
		new Player("a", "b", "ab", "abc").play(new RegexGame());
	}

}
