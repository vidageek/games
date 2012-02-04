package net.vidageek.games.regex.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vidageek.games.task.IndexedTask;
import net.vidageek.games.task.Task;

public class Tasks {

	private final List<Task> tasks = new ArrayList<Task>();

	public void add(final Task task) {
		tasks.add(task);
	}

	public IndexedTask at(final int index) {
		return new IndexedTask(tasks.get(index), index);
	}

	public int size() {
		return tasks.size();
	}

	public List<IndexedTask> all() {
		return unmodifiableIndexedTasks();
	}

	private List<IndexedTask> unmodifiableIndexedTasks() {
		List<IndexedTask> list = new ArrayList<IndexedTask>();
		int i = 0;
		for (Task task : tasks) {
			list.add(new IndexedTask(task, i++));
		}
		return Collections.unmodifiableList(list);
	}

}
