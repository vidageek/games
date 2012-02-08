package net.vidageek.games.regex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.vidageek.games.regex.task.RegexGame;
import net.vidageek.games.regex.test.infra.Person;

import org.junit.Test;

final public class RegexGameTest {

	@Test
	public void allTasksMustHaveAnswers() {
		new Person(
				// TODO: Review this, strange control index
				"a", "b", "ab", "abc", "\\\\", "\\$", "abcdefg", "ab\\$cd\\^ef\\\\g", "[ab]", "[ab]d", "[abc]d",
				"[a-c]", "[a-cA-D]", "[0-2]", "\\d", "\\da", "[\\da]", "\\s", "\\sa", "[\\sa]", "\\w", "\\wp", ".",
				"[^ab]", "[^ab]d", "[^abc]d", "\\D", "\\Da", "[^\\da]", "\\S", "\\Sa", "[^\\sa]", "\\W", "\\Wp", "a?",
				"([a-z]+)", "([a-z]+).*", "([a-z]+)(.*)", "(([a-z]+)(.*)a)").play(new RegexGame());
	}

	@Test
	public void hasNextTaskReturnsTrueIfThereIsSuchTask() {
		RegexGame game = new RegexGame();
		assertTrue(game.hasNextTask(0));
	}

	@Test
	public void hasNextTaskReturnsFalseIfThereIsNoSuchTask() {
		RegexGame game = new RegexGame();
		assertFalse(game.hasNextTask(game.size() + 1));
	}

	@Test
	public void nextTaskReturnsNextTask() {
		RegexGame game = new RegexGame();
		assertEquals(4, game.nextTask(3));
	}

}
