package net.vidageek.games.regex.task;

import static org.junit.Assert.assertEquals;
import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Faileds;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;

public class FailedsTest {

	private Faileds faileds;

	@Before
	public void setup() throws Exception {
		faileds = new Faileds();
		faileds.addOnlyJudgedFailed(new Failed("[aPattern] não dá match em [matchingTarget]"));
		faileds.addOnlyJudgedFailed(new Failed("[aPattern1] não dá match em [matchingTarget1]"));
	}
	
	@Test
	public void shouldShowDescriptionToAnyFaileds() {
		assertEquals(Joiner.on("<br>").join(faileds), faileds.getReason());
	}

	@Test
	public void shouldShowDescriptionToAllFaileds() {
		Faileds faileds = new Faileds();
		faileds.addAll(this.faileds);
		assertEquals(Joiner.on("<br>").join(faileds), faileds.getReason());
	}
}
