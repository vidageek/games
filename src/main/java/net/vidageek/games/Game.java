package net.vidageek.games;

import java.util.Collection;

import net.vidageek.games.task.Task;

public interface Game {

	int size();

	Task task(int index);

	String getDescription();

	Collection<? extends Task> getTasks();

	String getName();
}
