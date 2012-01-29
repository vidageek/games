package net.vidageek.games.regex.test.infra;

import static org.junit.Assert.assertTrue;
import net.vidageek.games.Game;

final public class Player {

	private final String[] answers;

	public Player(final String... answers) {
		this.answers = answers;
	}

	public void play(final Game game) {
		for (int i = 0; i < game.size(); i++) {
			assertTrue(	game.getClass().getSimpleName() + " task " + i + " is not correctly answered by " + answers[i],
						game.task(i).judge(answers[i]).ok());
		}
	}

}
