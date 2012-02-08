package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.fromStrings;

import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.task.CharClassRegex;
import net.vidageek.games.task.Task;

final public class RegexGame implements Game {

	private final Tasks tasks;

	public RegexGame() {
		tasks = new Tasks();
		addExercises1();
		addExercises2();
		addExercises3();
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
		tasks.add(new CharClassRegex(fromStrings("a", "b")));
		tasks.add(new CharClassRegex(fromStrings("ad", "bd")));
		tasks.add(new CharClassRegex(fromStrings("ad", "bd", "cd")));
		tasks.add(new CharClassRegex(fromStrings("a", "b", "c")));
		tasks.add(new CharClassRegex(fromStrings("a", "b", "c", "A", "B", "C", "D")));
		tasks.add(new CharClassRegex(fromStrings("0", "1", "2")));
		tasks.add(new CharClassRegex(fromStrings("1", "4", "5")));
		tasks.add(new CharClassRegex(fromStrings("1a", "4a", "5a")));
		tasks.add(new CharClassRegex(fromStrings("1", "4", "5", "a")));
		tasks.add(new CharClassRegex(fromStrings(" ", "\t", "\n", "\f", "\r")));
		tasks.add(new CharClassRegex(fromStrings(" a", "\ta", "\na")));
		tasks.add(new CharClassRegex(fromStrings(" ", "\t", "\n", "a")));
		tasks.add(new CharClassRegex(fromStrings("a", "b", "9")));
		tasks.add(new CharClassRegex(fromStrings("ap", "bp", "9p")));
		tasks.add(new CharClassRegex(fromStrings("a", "B", "9", "$", "\t", " ")));
	}

	private void addExercises3() {
		tasks.add(new NegateCharClassRegex(fromStrings("a", "b"), fromStrings("c", "d")));
		tasks.add(new NegateCharClassRegex(fromStrings("ad", "bd"), fromStrings("cd", "dd")));
		tasks.add(new NegateCharClassRegex(fromStrings("ad", "bd", "cd"), fromStrings("dd", "ed")));
		tasks.add(new NegateCharClassRegex(fromStrings("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
				fromStrings(" ", "a")));
		tasks.add(new NegateCharClassRegex(fromStrings("1a", "4a", "5a"), fromStrings(" a", "$a")));
		tasks.add(new NegateCharClassRegex(fromStrings("1", "4", "5", "a"), fromStrings(" ", "b")));
		tasks.add(new NegateCharClassRegex(fromStrings("\t", "\n", "\f", "\r"), fromStrings("A", "w")));
		tasks.add(new NegateCharClassRegex(fromStrings(" a", "\ta", "\na"), fromStrings("ca", "#a")));
		tasks.add(new NegateCharClassRegex(fromStrings(" ", "\t", "\n", "a"), fromStrings("Z", "A")));
		tasks.add(new NegateCharClassRegex(fromStrings("a", "B", "9"), fromStrings(" ", "$")));
		tasks.add(new NegateCharClassRegex(fromStrings("ap", "Bp", "9p"), fromStrings("$p", "#p")));
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
		return tasks.at(index);
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

	public boolean hasNextTask(final int index) {
		return nextTask(index) < size();
	}

	public int nextTask(final int index) {
		return index + 1;
	}

}
