package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.vidageek.games.task.JudgedTask;

import org.junit.Test;

final public class SingleCaptureGroupTest {

	@Test
	public void shouldCaptureASingleGroup() {
		JudgedTask judge = new SingleCaptureGroup("abcdef", 0).judge("([a-z]+)");
		assertTrue(judge.ok());
	}

	@Test
	public void shouldContainACaptureGroup() {
		JudgedTask judge = new SingleCaptureGroup("abcdef", 0).judge("[a-z]+");
		assertFalse(judge.ok());
		assertEquals(Failed.class, judge.getClass());
	}

	@Test
	public void shouldFailIfDoesNotMatch() {
		JudgedTask judge = new SingleCaptureGroup("abcdef", 0).judge("(\\d+)");
		assertFalse(judge.ok());
		assertEquals(Failed.class, judge.getClass());
	}

	@Test
	public void shouldFailIfGroup0IsNotTheMatchingTarget() {
		JudgedTask judge = new SingleCaptureGroup("abcdef", 0).judge("([a-d]+)");
		assertFalse(judge.ok());
		assertEquals(Failed.class, judge.getClass());
	}

	@Test
	public void shouldFailIfGroup1IsNotTheMatchingTarget() {
		JudgedTask judge = new SingleCaptureGroup("abcdef", 0).judge("([a-d]+)ef");
		assertFalse(judge.ok());
		assertEquals(Failed.class, judge.getClass());
	}

	@Test
	public void shouldReturnErrorIfRegexIsNotParseable() {
		JudgedTask judge = new SingleCaptureGroup("abcdef", 0).judge("([a-d]+ef");
		assertFalse(judge.ok());
		assertEquals(Error.class, judge.getClass());
	}

}
