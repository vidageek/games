package net.vidageek.games.regex;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.regex.task.CaptureGroup;
import net.vidageek.games.regex.task.OperatorMatcher;
import net.vidageek.games.regex.task.PerfectMatchRegex;
import net.vidageek.games.task.Task;

final public class RegexGame implements Game {

	private final List<Task> list;

	public RegexGame() {
		list = new ArrayList<Task>();
		addExercises1();
		addExercises4();
		addExercises5();
	}

	private boolean addExercises4() {
		return list.add(new OperatorMatcher(list.size(), "a"));
	}

	private void addExercises5() {
		list.add(new CaptureGroup(list.size(), "abcdef", "abcdef"));
		list.add(new CaptureGroup(list.size(), "abcdef1a", "abcdef"));
		list.add(new CaptureGroup(list.size(), "abcdef1a", "abcdef", "1a"));
		list.add(new CaptureGroup(list.size(), "abcdef1a", "abcdef1a", "abcdef", "1"));
	}

	private void addExercises1() {
		list.add(new PerfectMatchRegex(list.size(), "a"));
		list.add(new PerfectMatchRegex(list.size(), "b"));
		list.add(new PerfectMatchRegex(list.size(), "ab"));
		list.add(new PerfectMatchRegex(list.size(), "abc"));
		list.add(new PerfectMatchRegex(list.size(), "\\"));
		list.add(new PerfectMatchRegex(list.size(), "$"));
		list.add(new PerfectMatchRegex(list.size(), "abcdefg"));
		list.add(new PerfectMatchRegex(list.size(), "ab$cd^ef\\g"));
	}

	public Task task(final int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}

	public String getDescription() {
		return "Um jogo muito legal para aprender RegEx";
	}

	public List<Task> getTasks() {
		return list;
	}

	public String getName() {
		return "RegEx";
	}

}
