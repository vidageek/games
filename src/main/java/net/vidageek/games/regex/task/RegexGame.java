package net.vidageek.games.regex.task;

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
		tasks.add(new CharClassRegex("a", "b"));
		tasks.add(new CharClassRegex("ad", "bd"));
		tasks.add(new CharClassRegex("ad", "bd", "cd"));
		tasks.add(new CharClassRegex("a", "b", "c"));
		tasks.add(new CharClassRegex("a", "b", "c", "A", "B", "C", "D"));
		tasks.add(new CharClassRegex("0", "1", "2"));
		tasks.add(new CharClassRegex("1", "4", "5"));
		tasks.add(new CharClassRegex("1a", "4a", "5a"));
		tasks.add(new CharClassRegex("1", "4", "5", "a"));
		tasks.add(new CharClassRegex(" ", "\t", "\n", "\f", "\r"));
		tasks.add(new CharClassRegex(" a", "\ta", "\na"));
		tasks.add(new CharClassRegex(" ", "\t", "\n", "a"));
		tasks.add(new CharClassRegex("a", "b", "9"));
		tasks.add(new CharClassRegex("ap", "bp", "9p"));
		tasks.add(new CharClassRegex("a", "B", "9", "$", "\t", " "));
	}

	private void addExercises3() {
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("a", "b"), MatcherTargets.fromStrings("c", "d")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("ad", "bd"), MatcherTargets.fromStrings("cd", "dd")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("ad", "bd", "cd"), MatcherTargets.fromStrings("dd", "ed")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), MatcherTargets.fromStrings(" ", "a")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("1a", "4a", "5a"), MatcherTargets.fromStrings(" a", "$a")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("1", "4", "5", "a"), MatcherTargets.fromStrings(" ", "b")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("\t", "\n", "\f", "\r"), MatcherTargets.fromStrings("A", "w")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings(" a", "\ta", "\na"), MatcherTargets.fromStrings("ca", "#a")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings(" ", "\t", "\n", "a"), MatcherTargets.fromStrings("Z", "A")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("a", "B", "9"), MatcherTargets.fromStrings(" ", "$")));
		tasks.add(new NegateCharClassRegex(MatcherTargets.fromStrings("ap", "Bp", "9p"), MatcherTargets.fromStrings("$p", "#p")));
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

}
