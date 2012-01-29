package net.vidageek.games.regex;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.games.Game;
import net.vidageek.games.regex.task.PerfectMatchRegexTask;
import net.vidageek.games.task.Task;

final public class RegexGame implements Game {

	private final List<Task> list;

	public RegexGame() {
		list = new ArrayList<Task>();
		list.add(new PerfectMatchRegexTask("a", 0));
		list.add(new PerfectMatchRegexTask("b", 1));
		list.add(new PerfectMatchRegexTask("ab", 2));
		list.add(new PerfectMatchRegexTask("abc", 3));
	}

	public Task task(final int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}

}
