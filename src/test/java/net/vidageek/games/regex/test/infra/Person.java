package net.vidageek.games.regex.test.infra;

import static org.junit.Assert.assertTrue;
import net.vidageek.games.Game;

final public class Person {

	private final String[] answers;

	public Person(final String... answers) {
		this.answers = answers;
	}

	public void play(final Game game) {
		assertTrue("Number of tasks and answers should be equals!", game.size() == this.answers.length);
		for (int i = 0; i < game.size(); i++) {
			assertTrue(game.getClass().getSimpleName() + " task " + i + " is not correctly answered by " + answers[i],
						game.task(i).judge(answers[i]).getOk());
		}
	}

}
