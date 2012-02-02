package net.vidageek.games.regex;

import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.regex.task.CaptureGroup;
import net.vidageek.games.regex.task.MultipleAnswersMatcher;
import net.vidageek.games.regex.task.OperatorMatcher;
import net.vidageek.games.regex.task.PerfectMatchRegex;
import net.vidageek.games.task.Task;

final public class RegexGame implements Game {

	private final Tasks tasks;

	public RegexGame() {
		tasks = new Tasks();
		addExercises1();
		addExercises2();
		addExercises4();
		addExercises5();
	}

	private void addExercises1() {
		tasks.add(new PerfectMatchRegex("a"));
		tasks.add(new PerfectMatchRegex("b"));
		tasks.add(new PerfectMatchRegex("ab"));
		tasks.add(new PerfectMatchRegex("abc"));
		tasks.add(new PerfectMatchRegex("\\"));
		tasks.add(new PerfectMatchRegex("$"));
		tasks.add(new PerfectMatchRegex("abcdefg"));
		tasks.add(new PerfectMatchRegex("ab$cd^ef\\g"));
	}

	private void addExercises2() {
		tasks.add(new MultipleAnswersMatcher("a", "b"));
		tasks.add(new MultipleAnswersMatcher("ad", "bd"));
		tasks.add(new MultipleAnswersMatcher("ad", "bd", "cd"));
		tasks.add(new MultipleAnswersMatcher("a", "b", "c"));
		tasks.add(new MultipleAnswersMatcher("a", "b", "c", "A", "B", "C", "D"));
		tasks.add(new MultipleAnswersMatcher("0", "1", "2"));
		tasks.add(new MultipleAnswersMatcher("1", "4", "5"));
		tasks.add(new MultipleAnswersMatcher("1a", "4a", "5a"));
		tasks.add(new MultipleAnswersMatcher("1", "4", "5", "a"));
		tasks.add(new MultipleAnswersMatcher(" ", "\t", "\n", "\f", "\r"));
		tasks.add(new MultipleAnswersMatcher(" a", "\ta", "\na"));
		tasks.add(new MultipleAnswersMatcher(" ", "\t", "\n", "a"));
		tasks.add(new MultipleAnswersMatcher("a", "b", "9"));
		tasks.add(new MultipleAnswersMatcher("ap", "bp", "9p"));
		tasks.add(new MultipleAnswersMatcher("a", "B", "9", "$", "\t", " "));
	}

	private void addExercises4() {
		tasks.add(new OperatorMatcher("a"));
	}

	private void addExercises5() {
		tasks.add(new CaptureGroup("abcdef", "abcdef"));
		tasks.add(new CaptureGroup("abcdef1a", "abcdef"));
		tasks.add(new CaptureGroup("abcdef1a", "abcdef", "1a"));
		tasks.add(new CaptureGroup("abcdef1a", "abcdef1a", "abcdef", "1"));
	}

	public Task task(final int index) {
		return tasks.in(index);
	}

	public int size() {
		return tasks.size();
	}

	public String getDescription() {
		return "Um jogo muito legal para aprender RegEx";
	}

	public List<? extends Task> getTasks() {
		return tasks.all();
	}

	public String getName() {
		return "RegEx";
	}

}
