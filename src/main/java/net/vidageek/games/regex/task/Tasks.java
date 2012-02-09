package net.vidageek.games.regex.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vidageek.games.task.IndexedTask;
import net.vidageek.games.task.Task;

public class Tasks {

	private final List<TaskGroup> taskGroups = new ArrayList<TaskGroup>();

	public void add(final TaskGroup group) {
		taskGroups.add(group);
	}

	public IndexedTask at(final int originalIndex) {
		int index = originalIndex;
		int groupCount = 0;
		while ((groupCount < taskGroups.size()) && (index - taskGroups.get(groupCount).size() >= 0)) {
			index -= taskGroups.get(groupCount).size();
			groupCount++;
		}
		TaskGroup group = taskGroups.get(groupCount);
		return new IndexedTask(new GroupedTask(group, group.task(index)), originalIndex);
	}

	public int size() {
		int res = 0;
		for (TaskGroup group : taskGroups) {
			res += group.size();
		}
		return res;
	}

	public List<IndexedTask> all() {
		return unmodifiableIndexedTasks();
	}

	private List<IndexedTask> unmodifiableIndexedTasks() {
		List<IndexedTask> list = new ArrayList<IndexedTask>();
		int i = 0;
		for (TaskGroup group : taskGroups) {
			for (Task task : group) {
				list.add(new IndexedTask(new GroupedTask(group, task), i++));
			}
		}
		return Collections.unmodifiableList(list);
	}

}
