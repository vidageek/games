package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

import org.junit.Test;

final public class TasksTest {

	@Test
	public void allTasksMustObeyIndexing() {
		Tasks tasks = new Tasks();
		TaskGroup group = new TaskGroup("", null);
		group.add(new TestTask());
		group.add(new TestTask());
		tasks.add(group);
		group = new TaskGroup("", null);
		group.add(new TestTask());
		group.add(new TestTask());
		tasks.add(group);
		group = new TaskGroup("", null);
		group.add(new TestTask());
		group.add(new TestTask());
		tasks.add(group);

		for (int i = 0; i < 6; i++) {
			assertEquals(i, tasks.at(i).getIndex());
		}
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
