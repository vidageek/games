package net.vidageek.games;

import net.vidageek.games.task.Task;

public interface Game {

	int size();

	Task task(int index);
}
