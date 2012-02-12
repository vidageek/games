package net.vidageek.games.regex.task;

import static net.vidageek.games.regex.task.MatcherTargets.from;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.junit.Test;

public class MatcherTargetsTest {

	@Test
	public void shouldInterateOverMatcherTargets() {
		assertThat(from("a", "b"), hasItems("a", "b"));
	}

	@Test
	public void shouldBuildGoodHtmlFor1Target() {
		assertThat(from("A").asHtml(), equalTo("<code>A</code>"));
	}

	@Test
	public void shouldBuildGoodHtmlFor2Targets() {
		assertThat(from("A", "B").asHtml(), equalTo("<code>A</code> e <code>B</code>"));
	}

	@Test
	public void shouldBuildGoodHtmlFor3Targets() {
		assertThat(from("a", "b", "c").asHtml(), equalTo("<code>a</code>, <code>b</code> e <code>c</code>"));
	}

}
