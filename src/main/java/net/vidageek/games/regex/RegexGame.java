package net.vidageek.games.regex;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.regex.task.CaptureGroup;
import net.vidageek.games.regex.task.MultipleAnswersMatcher;
import net.vidageek.games.regex.task.OperatorMatcher;
import net.vidageek.games.regex.task.PerfectMatchRegex;
import net.vidageek.games.task.Task;

final public class RegexGame implements Game {

	private final List<Task> list;

	public RegexGame() {
		list = new ArrayList<Task>();
		addExercises1();
		addExercises2();
		addExercises4();
		addExercises5();
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

	private void addExercises2() {
		list.add(new MultipleAnswersMatcher(list.size(), "a", "b"));
		list.add(new MultipleAnswersMatcher(list.size(), "ad", "bd"));
		list.add(new MultipleAnswersMatcher(list.size(), "ad", "bd", "cd"));
		list.add(new MultipleAnswersMatcher(list.size(), "a", "b", "c"));
		list.add(new MultipleAnswersMatcher(list.size(), "a", "b", "c", "A", "B", "C", "D"));
		list.add(new MultipleAnswersMatcher(list.size(), "0", "1", "2"));
		list.add(new MultipleAnswersMatcher(list.size(), "1", "4", "5"));
		list.add(new MultipleAnswersMatcher(list.size(), "1a", "4a", "5a"));
		list.add(new MultipleAnswersMatcher(list.size(), "1", "4", "5", "a"));
		list.add(new MultipleAnswersMatcher(list.size(), " ", "\t", "\n", "\f", "\r"));
		list.add(new MultipleAnswersMatcher(list.size(), " a", "\ta", "\na"));
		list.add(new MultipleAnswersMatcher(list.size(), " ", "\t", "\n", "a"));
		list.add(new MultipleAnswersMatcher(list.size(), "a", "b", "9"));
		list.add(new MultipleAnswersMatcher(list.size(), "ap", "bp", "9p"));
		list.add(new MultipleAnswersMatcher(list.size(), "a", "B", "9", "$", "\t", " "));
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
