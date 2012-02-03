package net.vidageek.games.regex.task;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

import net.vidageek.games.task.JudgedTask;

public class Faileds implements JudgedTask {

	private List<Failed> faileds = new ArrayList<Failed>(); 
	
	public boolean ok() {
		return this.faileds.isEmpty();
	}

	public String getReason() {
		return Joiner.on("<br>").join(faileds);
	}

	public void addOnlyJudgedFailed(JudgedTask judgedTask) {
		if (judgedTask instanceof Failed)
			this.faileds.add((Failed)judgedTask);
	}

}
