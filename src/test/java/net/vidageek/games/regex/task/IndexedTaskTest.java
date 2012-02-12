package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.fromStrings;
import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.IndexedTask;
import net.vidageek.games.task.MultipleMatch;
import net.vidageek.games.task.status.Error;

import org.junit.Test;

public class IndexedTaskTest {

	@Test
	public void shouldReturnJudgedTaskWithErrorWhenInvalidRegex() {
		assertEquals(Error.class, new IndexedTask(new GroupedTask(new TaskGroup("", null), new MultipleMatch(
				fromStrings("a"))), 1).judge("aIncalidRegex)").getClass());
	}

}
