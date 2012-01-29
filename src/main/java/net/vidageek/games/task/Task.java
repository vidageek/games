package net.vidageek.games.task;

public interface Task {

	JudgedTask judge(String challenge);

	String getDescription();

	String getChallenge();

	int getIndex();
}
