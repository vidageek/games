package net.vidageek.games.regex;

import net.vidageek.games.Game;
import net.vidageek.games.GameController;
import br.com.caelum.vraptor.ioc.Component;

@Component
final public class RegexGame implements Game {

	public Class<? extends GameController> beginClass() {
		return RegexGameController.class;
	}

	public Task task(final int index) {
		return new RegexTask("a", index);
	}

}
