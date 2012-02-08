package net.vidageek.games;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Ok;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;

final public class GameConsoleTest {

	@Mock
	private Result result;

	@Mock(answer = RETURNS_DEEP_STUBS)
	private Game game;

	@Mock
	private GameConsole gameConsole;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(result.redirectTo(any(GameConsole.class))).thenReturn(gameConsole);
	}

	@Test
	public void shouldRedirectToSameTaskWhenTaskIsIncorrect() {
		final Failed judgedTask = new Failed("asdf");
		when(game.task(3).judge("challenge")).thenReturn(judgedTask);

		new GameConsole(result, game).submit("name", 3, "challenge");

		verify(gameConsole).task("name", 3);
		verify(result).include("judgedTask", judgedTask);
	}

	@Test
	public void shouldRedirectToGameBeginIfThereAreNoMoreTasks() {
		final Ok judgedTask = new Ok();
		when(game.task(3).judge("challenge")).thenReturn(judgedTask);
		when(game.size()).thenReturn(4);

		new GameConsole(result, game).submit("name", 3, "challenge");

		verify(gameConsole).index("name");
		verify(result).include("judgedTask", judgedTask);
	}

	@Test
	public void shouldRedirectToNextTaskIfTaskIsOk() {
		final Ok judgedTask = new Ok();
		when(game.task(3).judge("challenge")).thenReturn(judgedTask);
		when(game.hasNextTask(anyInt())).thenReturn(true);
		when(game.nextTask(anyInt())).thenReturn(4);

		new GameConsole(result, game).submit("name", 3, "challenge");

		verify(gameConsole).task("name", 4);
		verify(result).include("judgedTask", judgedTask);
	}
}
