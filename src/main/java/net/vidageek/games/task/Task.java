package net.vidageek.games.task;

public interface Task {

	JudgedTask judge(String challenge);

	String getChallenge();
}
