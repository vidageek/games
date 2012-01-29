package net.vidageek.games.regex;

import net.vidageek.games.task.JudgedTask;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class GameController {

	private final Result result;
	private final RegexGame game;

	public GameController(final Result result, final RegexGame game) {
		this.result = result;
		this.game = game;
	}

	@Get("/play/{gameName}")
	public void index(final String gameName) {
		result.include("gameName", gameName);
	}

	@Get("/play/{gameName}/task/{index}")
	public void task(final String gameName, final int index) {
		result.include("gameName", gameName);
		result.include("task", game.task(index));
	}

	@Post("/play/{gameName}/task/submit/{index}")
	public void submit(final String gameName, final int index, final String challenge) {
		final JudgedTask judgedTask = game.task(index).judge(challenge);
		result.include("judgedTask", judgedTask);
		if (judgedTask.ok()) {
			if (index + 1 < game.size()) {
				result.redirectTo(this).task(gameName, index + 1);
			} else {
				result.redirectTo(this).index(gameName);
			}
		} else {
			result.redirectTo(this).task(gameName, index);
		}
	}
}
