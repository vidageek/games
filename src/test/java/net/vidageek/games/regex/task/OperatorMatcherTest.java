package net.vidageek.games.regex.task;

import static org.junit.Assert.assertTrue;
import net.vidageek.games.task.JudgedTask;

import org.junit.Test;

/**
 * 
 * @author juliano alves
 * 
 */
public class OperatorMatcherTest {

	@Test
	public void shouldMatchWithZeroOrOneOperator() {
		JudgedTask judge = new OperatorMatcher(0, "a").judge("a?");
		assertTrue(judge.ok());
	}
}
