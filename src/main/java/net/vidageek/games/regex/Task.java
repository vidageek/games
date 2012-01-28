package net.vidageek.games.regex;

public interface Task {

	JudgedTask judge(String challenge);

	String getDescription();

	String getChallenge();

	int getIndex();
}
