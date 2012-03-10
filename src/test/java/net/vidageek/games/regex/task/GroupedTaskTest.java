package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import net.vidageek.games.regex.Descriptions;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

final public class GroupedTaskTest {

	@Mock
	private Descriptions descriptions;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnGroupDescription() {
		when(descriptions.forGroup("a")).thenReturn("description");

		GroupedTask task = new GroupedTask(new TaskGroup("b", "a", descriptions), new TestTask());
		assertEquals("description", task.getDescription());
	}

	public class TestTask implements Task {

		public JudgedTask judge(final String challenge) {
			return null;
		}

		public String getChallenge() {
			return null;
		}

	}

}
