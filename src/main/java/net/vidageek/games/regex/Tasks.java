package net.vidageek.games.regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vidageek.games.task.Task;

public class Tasks {

	private List<Task> tasks = new ArrayList<Task>(); 
	
	public void add(final Task task) {
		this.tasks.add(task.withIndex(tasks.size()));
	}

	public Task in(final int index) {
		return this.tasks.get(index);
	}

	public int size() {
		return tasks.size();
	}

	public List<Task> all() {
		return Collections.unmodifiableList(this.tasks);
	}

}
