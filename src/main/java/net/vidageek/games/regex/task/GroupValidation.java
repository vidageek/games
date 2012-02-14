package net.vidageek.games.regex.task;

import java.util.regex.Matcher;

import net.vidageek.games.task.JudgedTask;

public interface GroupValidation {

	JudgedTask judge(String challenge, Matcher matcher);

}
