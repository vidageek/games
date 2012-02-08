package net.vidageek.games;

import net.vidageek.games.task.JudgedTask;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class GameConsole {

	private final Result result;
	private final Game game;

	public GameConsole(final Result result, final Game game) {
		this.result = result;
		this.game = game;
	}

	@Get("/play/{gameName}")
	public void index(final String gameName) {
		result.include("gameName", gameName);
		result.include("game", game);
	}

	@Get("/play/{gameName}/task/{index}")
	public void task(final String gameName, final int index) {
		result.include("gameName", gameName);
		result.include("task", game.task(index));
	}

	@Post("/play/{gameName}/task/{index}")
	public void submit(final String gameName, final int index, final String challenge) {
		final JudgedTask judgedTask = game.task(index).judge(challenge);
		result.include("judgedTask", judgedTask);
		if (judgedTask.ok()) {
			if (game.hasNextTask(index)) {
				result.redirectTo(this).task(gameName, game.nextTask(index));
			} else {
				result.redirectTo(this).index(gameName);
			}
		} else {
			result.redirectTo(this).task(gameName, index);
		}
	}
}
