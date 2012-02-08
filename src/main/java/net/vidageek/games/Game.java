package net.vidageek.games;

import java.util.Collection;

import net.vidageek.games.task.TaskWithDescription;

public interface Game {

	int size();

	TaskWithDescription task(int index);

	String getDescription();

	Collection<? extends TaskWithDescription> getTasks();

	String getName();

	boolean hasNextTask(int index);

	int nextTask(int index);
}
