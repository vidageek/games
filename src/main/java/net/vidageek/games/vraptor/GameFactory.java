package net.vidageek.games.vraptor;

import net.vidageek.games.Game;
import net.vidageek.games.regex.Descriptions;
import net.vidageek.games.regex.task.RegexGame;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
final public class GameFactory implements ComponentFactory<Game> {

	private final Descriptions descriptions;

	public GameFactory(final Descriptions descriptions) {
		this.descriptions = descriptions;
	}

	public Game getInstance() {
		return new RegexGame(descriptions);
	}

}
