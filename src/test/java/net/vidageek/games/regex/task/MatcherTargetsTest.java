package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.junit.Test;

public class MatcherTargetsTest {

	@Test
	public void shouldInterateOverMatcherTargets() {
		assertThat(aMatcherTargetWithAAndB(), hasItems("a", "b"));
	}

	private MatcherTargets aMatcherTargetWithAAndB() {
		return MatcherTargets.from("a", "b");
	}

	@Test
	public void shouldBuildGoodHtmlFor2Target() {
		assertThat(from("A", "B").asHtml(), equalTo("<code>A</code> e <code>B</code>"));
	}

	@Test
	public void shouldBuildGoodHtmlFor3Target() {
		assertThat(from("a", "b", "c").asHtml(), equalTo("<code>a</code>, <code>b</code> e <code>c</code>"));
	}

}
