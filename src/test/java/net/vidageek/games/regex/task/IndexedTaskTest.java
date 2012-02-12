package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;
import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.IndexedTask;
import net.vidageek.games.task.Match;
import net.vidageek.games.task.status.Error;

import org.junit.Test;

public class IndexedTaskTest {

	@Test
	public void shouldReturnJudgedTaskWithErrorWhenInvalidRegex() {
		assertEquals(Error.class, new IndexedTask(new GroupedTask(new TaskGroup("", null), new Match(
				from("a"))), 1).judge("aIncalidRegex)").getClass());
	}

}
