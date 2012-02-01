package net.vidageek.games.regex.task;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.internal.runners.statements.Fail;

import net.vidageek.games.regex.Regex;
import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

public class MultipleAnswersMatcher implements Task {

	private final int index;
	private final String[] matchingTargets;

	public MultipleAnswersMatcher(int index, String...matchingTargets) {
		this.index = index;
		this.matchingTargets = matchingTargets;
		
	}

	public JudgedTask judge(String challenge) {
		try {
			Regex regex = new Regex(challenge);
			Faileds fails = regex.matchAll(this.matchingTargets);
			return fails.ok()? new Ok() : fails;
			
		} catch (Exception e) {
			return new Error(e);
		}
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getChallenge() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
