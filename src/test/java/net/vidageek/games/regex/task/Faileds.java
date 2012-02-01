package net.vidageek.games.regex.task;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.games.task.JudgedTask;

public class Faileds implements JudgedTask {

	private List<Failed> faileds = new ArrayList<Failed>(); 
	
	public boolean ok() {
		return this.faileds.isEmpty();
	}

	public String getReason() {
		
		return null;
	}

	public void addOnlyJudgedFailed(JudgedTask judgedTask) {
		if (judgedTask instanceof Failed)
			this.faileds.add((Failed)judgedTask);
	}

}
