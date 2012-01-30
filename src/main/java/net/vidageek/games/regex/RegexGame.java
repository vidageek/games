package net.vidageek.games.regex;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.regex.task.PerfectMatchRegex;
import net.vidageek.games.task.Task;

final public class RegexGame implements Game {

	private final List<Task> list;

	public RegexGame() {
		list = new ArrayList<Task>();
		list.add(new PerfectMatchRegex("a", 0));
		list.add(new PerfectMatchRegex("b", 1));
		list.add(new PerfectMatchRegex("ab", 2));
		list.add(new PerfectMatchRegex("abc", 3));
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
