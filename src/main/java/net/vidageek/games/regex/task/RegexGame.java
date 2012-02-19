package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;

import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.regex.Descriptions;
import net.vidageek.games.task.Match;
import net.vidageek.games.task.TaskWithDescription;

final public class RegexGame implements Game {

	private final Tasks tasks;

	public RegexGame(final Descriptions descriptions) {
		tasks = new Tasks();
		addCharsExercises(descriptions);
		addCharClassesExercises(descriptions);
		addOpositeCharClassExercises(descriptions);
		addPipeOperator(descriptions);
		addOperatorsExercises(descriptions);
		addCaptureGroupExercises(descriptions);
	}

	private void addCharsExercises(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.chars", descriptions);
		group.add(new Match(from("a")));
		group.add(new Match(from("b")));
		group.add(new Match(from("ab")));
		group.add(new Match(from("abc")));
		group.add(new Match(from("\\")));
		group.add(new Match(from("$")));
		group.add(new Match(from("abcdefg12345")));
		group.add(new Match(from("Ab5")));
		group.add(new Match(from("AbCdEfG6")));
		group.add(new Match(from("ab$cd^Ef\\G1")));
		tasks.add(group);
	}

	private void addCharClassesExercises(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.chars.classes", descriptions);
		group.add(new Match(from("a", "b")));
		group.add(new Match(from("ad", "bd")));
		group.add(new Match(from("ad", "bd", "cd")));
		group.add(new Match(from("a", "b", "c")));
		group.add(new Match(from("a", "b", "c", "A", "B", "C", "D")));
		group.add(new Match(from("0", "1", "2")));
		group.add(new Match(from(	"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
									"q",
									"r", "s", "t", "u", "v", "x", "w", "y", "z")));
		group.add(new Match(from("1", "4", "5")));
		group.add(new Match(from("1a", "4a", "5a")));
		group.add(new Match(from("1", "4", "5", "a")));
		group.add(new Match(from(" ", "\t", "\n", "\f", "\r")));
		group.add(new Match(from(" a", "\ta", "\na")));
		group.add(new Match(from(" ", "\t", "\n", "a")));
		group.add(new Match(from("a", "b", "9")));
		group.add(new Match(from("ap", "bp", "9p")));
		group.add(new Match(from("a", "B", "9", "$", "\t", " ")));
		tasks.add(group);
	}

	private void addOpositeCharClassExercises(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.negate", descriptions);
		group.add(new NegateAndMatch(from("a", "b"), from("c", "d")));
		group.add(new NegateAndMatch(from("ad", "bd"), from("cd", "dd")));
		group.add(new NegateAndMatch(from("ad", "bd", "cd"), from("dd", "ed")));
		group.add(new NegateAndMatch(from("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), from(" ", "a")));
		group.add(new NegateAndMatch(from("1a", "4a", "5a"), from(" a", "$a")));
		group.add(new NegateAndMatch(from("1", "4", "5", "a"), from(" ", "b")));
		group.add(new NegateAndMatch(from("\t", "\n", "\f", "\r"), from("A", "w")));
		group.add(new NegateAndMatch(from(" a", "\ta", "\na"), from("ca", "#a")));
		group.add(new NegateAndMatch(from(" ", "\t", "\n", "a"), from("Z", "A")));
		group.add(new NegateAndMatch(from("a", "B", "9"), from(" ", "$")));
		group.add(new NegateAndMatch(from("ap", "Bp", "9p"), from("$p", "#p")));
		tasks.add(group);
	}

	private void addPipeOperator(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.pipe", descriptions);
		group.add(new NegateAndMatch(from("ab", "ba"), from("a", "b")));
		group.add(new NegateAndMatch(from("ba", "baa"), from("aa", "bb", "ab")));
		tasks.add(group);
	}

	private void addOperatorsExercises(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.operators", descriptions);
		group.add(new NegateAndMatch(from("b"), from("", "a")));
		group.add(new NegateAndMatch(from("aa"), from("", "a", "b")));
		group.add(new NegateAndMatch(from("", "aaaaaaaaab"), from("a", "aa", "aaaaaaaaa")));
		group.add(new NegateAndMatch(from("", "abcdcba"), from("a", "abc", "cbaabc", "aabbcc")));
		group.add(new NegateAndMatch(from("abcdcba"), from("", "a", "abc", "cbaabc", "aabbcc")));
		group.add(new NegateAndMatch(from("aaaaaaaaab"), from("", "a", "aa", "aaaaaaaaa")));
		group.add(new NegateAndMatch(from("a", "aa"), from("aaa")));
		group.add(new NegateAndMatch(from("a", "ab", "abca"), from("abc", "cba")));
		tasks.add(group);
	}

	private void addCaptureGroupExercises(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.capture", descriptions);
		group.add(new CaptureGroup("abcdef", "abcdef"));
		group.add(new CaptureGroup("abcdef1a", "abcdef"));
		group.add(new CaptureGroup("abcdef1a", "abcdef", "1a"));
		group.add(new CaptureGroup("abcdef1a", "abcdef1a", "abcdef", "1"));
		group.add(new CaptureGroup("aa", "a"));
		group.add(new CaptureGroup("aaaa", "aa"));
		group.add(new CaptureGroup("aaaaaaaa", "aaaa"));
		tasks.add(group);
	}

	public TaskWithDescription task(final int index) {

		return tasks.at(index);
	}

	public int getSize() {
		return tasks.size();
	}

	public String getDescription() {
		return "Um jogo muito legal para aprender RegEx";
	}

	public List<? extends TaskWithDescription> getTasks() {
		return tasks.all();
	}

	public String getName() {
		return "RegEx";
	}

	public boolean hasNextTask(final int index) {
		return nextTask(index) < getSize();
	}

	public int nextTask(final int index) {
		return index + 1;
	}

}
