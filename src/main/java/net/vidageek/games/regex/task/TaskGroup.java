package net.vidageek.games.regex.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.vidageek.games.regex.Descriptions;
import net.vidageek.games.task.Task;

final public class TaskGroup implements Iterable<Task> {

	private final List<Task> tasks;
	private final Descriptions descriptions;
	private final String groupName;
	private final String name;

	public TaskGroup(String name, final String groupName, final Descriptions descriptions) {
		this.name = name;
		this.groupName = groupName;
		this.descriptions = descriptions;
		tasks = new ArrayList<Task>();
	}

	public void add(final Task task) {
		tasks.add(task);
	}

	public int size() {
		return tasks.size();
	}

	public Task task(final int i) {
		return tasks.get(i);
	}

	public Iterator<Task> iterator() {
		return Collections.unmodifiableCollection(tasks).iterator();
	}

	public String getDescription() {
		return descriptions.forGroup(groupName);
	}

	public String getName() {
		return name;
	}

}
