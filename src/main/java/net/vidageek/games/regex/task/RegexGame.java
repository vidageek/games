package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.fromStrings;

import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.regex.Descriptions;
import net.vidageek.games.task.MultipleMatch;
import net.vidageek.games.task.TaskWithDescription;

final public class RegexGame implements Game {

	private final Tasks tasks;

	public RegexGame(final Descriptions descriptions) {
		tasks = new Tasks();
		addExercises1(descriptions);
		addExercises2(descriptions);
		addExercises3(descriptions);
		addExercises4(descriptions);
		addExercises5(descriptions);
	}

	private void addExercises1(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.chars", descriptions);
		group.add(new MultipleMatch(fromStrings("a")));
		group.add(new MultipleMatch(fromStrings("b")));
		group.add(new MultipleMatch(fromStrings("ab")));
		group.add(new MultipleMatch(fromStrings("abc")));
		group.add(new MultipleMatch(fromStrings("\\")));
		group.add(new MultipleMatch(fromStrings("$")));
		group.add(new MultipleMatch(fromStrings("abcdefg12345")));
		group.add(new MultipleMatch(fromStrings("Ab5")));
		group.add(new MultipleMatch(fromStrings("AbCdEfG6")));
		group.add(new MultipleMatch(fromStrings("ab$cd^Ef\\G1")));
		tasks.add(group);
	}

	private void addExercises2(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.chars.classes", descriptions);
		group.add(new MultipleMatch(fromStrings("a", "b")));
		group.add(new MultipleMatch(fromStrings("ad", "bd")));
		group.add(new MultipleMatch(fromStrings("ad", "bd", "cd")));
		group.add(new MultipleMatch(fromStrings("a", "b", "c")));
		group.add(new MultipleMatch(fromStrings("a", "b", "c", "A", "B", "C", "D")));
		group.add(new MultipleMatch(fromStrings("0", "1", "2")));
		group.add(new MultipleMatch(fromStrings("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
												"o", "p", "q", "r", "s", "t", "u", "v", "x", "w", "y", "z")));
		group.add(new MultipleMatch(fromStrings("1", "4", "5")));
		group.add(new MultipleMatch(fromStrings("1a", "4a", "5a")));
		group.add(new MultipleMatch(fromStrings("1", "4", "5", "a")));
		group.add(new MultipleMatch(fromStrings(" ", "\t", "\n", "\f", "\r")));
		group.add(new MultipleMatch(fromStrings(" a", "\ta", "\na")));
		group.add(new MultipleMatch(fromStrings(" ", "\t", "\n", "a")));
		group.add(new MultipleMatch(fromStrings("a", "b", "9")));
		group.add(new MultipleMatch(fromStrings("ap", "bp", "9p")));
		group.add(new MultipleMatch(fromStrings("a", "B", "9", "$", "\t", " ")));
		tasks.add(group);
	}

	private void addExercises3(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.negate", descriptions);
		group.add(new NegateCharClassRegex(fromStrings("a", "b"), fromStrings("c", "d")));
		group.add(new NegateCharClassRegex(fromStrings("ad", "bd"), fromStrings("cd", "dd")));
		group.add(new NegateCharClassRegex(fromStrings("ad", "bd", "cd"), fromStrings("dd", "ed")));
		group.add(new NegateCharClassRegex(fromStrings("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
				fromStrings(" ", "a")));
		group.add(new NegateCharClassRegex(fromStrings("1a", "4a", "5a"), fromStrings(" a", "$a")));
		group.add(new NegateCharClassRegex(fromStrings("1", "4", "5", "a"), fromStrings(" ", "b")));
		group.add(new NegateCharClassRegex(fromStrings("\t", "\n", "\f", "\r"), fromStrings("A", "w")));
		group.add(new NegateCharClassRegex(fromStrings(" a", "\ta", "\na"), fromStrings("ca", "#a")));
		group.add(new NegateCharClassRegex(fromStrings(" ", "\t", "\n", "a"), fromStrings("Z", "A")));
		group.add(new NegateCharClassRegex(fromStrings("a", "B", "9"), fromStrings(" ", "$")));
		group.add(new NegateCharClassRegex(fromStrings("ap", "Bp", "9p"), fromStrings("$p", "#p")));
		tasks.add(group);
	}

	private void addExercises4(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.operators", descriptions);
		group.add(new OperatorMatcher("a"));
		tasks.add(group);
	}

	private void addExercises5(final Descriptions descriptions) {
		TaskGroup group = new TaskGroup("match.capture", descriptions);
		group.add(new CaptureGroup("abcdef", "abcdef"));
		group.add(new CaptureGroup("abcdef1a", "abcdef"));
		group.add(new CaptureGroup("abcdef1a", "abcdef", "1a"));
		group.add(new CaptureGroup("abcdef1a", "abcdef1a", "abcdef", "1"));
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
