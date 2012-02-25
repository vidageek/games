package net.vidageek.games.task.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.vidageek.games.task.JudgedTask;

import com.google.common.base.Joiner;

public class Faileds implements JudgedTask, Iterable<Failed> {

	private final List<Failed> faileds = new ArrayList<Failed>();

	public boolean getOk() {
		return faileds.isEmpty();
	}

	public String getReason() {
		return Joiner.on("<br />").join(faileds);
	}

	public void addOnlyJudgedFailed(final JudgedTask judgedTask) {
		if (judgedTask instanceof Failed) {
			faileds.add((Failed) judgedTask);
		}
	}

	public void addAll(final Faileds faileds) {
		for (Failed failed : faileds) {
			addOnlyJudgedFailed(failed);
		}
	}

	public JudgedTask judgment() {
		return getOk() ? new Ok() : new Failed(this);
	}

	public Iterator<Failed> iterator() {
		return Collections.unmodifiableList(faileds).iterator();
	}

}
