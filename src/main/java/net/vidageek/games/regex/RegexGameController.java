package net.vidageek.games.regex;

import net.vidageek.games.GameController;
import net.vidageek.games.GameView;
import net.vidageek.games.task.JudgedTask;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class RegexGameController implements GameController {

	private final Result result;
	private final RegexGame game;

	public RegexGameController(final Result result, final RegexGame game) {
		this.result = result;
		this.game = game;
	}

	@Get("/play/regex")
	public void index() {
		result.use(GameView.class).render("/regex/index.html");
	}

	@Get("/play/regex/task/{index}")
	public void task(final int index) {
		result.include("task", game.task(index));
	}

	@Post("/play/regex/task/submit/{index}")
	public void submit(final int index, final String challenge) {
		final JudgedTask judgedTask = game.task(index).judge(challenge);
		result.include("judgedTask", judgedTask);
		if (judgedTask.ok()) {
			if (index + 1 < game.size()) {
				result.redirectTo(this).task(index + 1);
			} else {
				result.redirectTo(this).index();
			}
		} else {
			result.redirectTo(this).task(index);
		}
	}
}
