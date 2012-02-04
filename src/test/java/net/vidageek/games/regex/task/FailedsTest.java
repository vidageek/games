package net.vidageek.games.regex.task;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.games.task.status.Failed;
import net.vidageek.games.task.status.Faileds;

import org.junit.Test;

import com.google.common.base.Joiner;

public class FailedsTest {

	@Test
	public void shouldShowDescriptionToAnyFaeleds() {
		
		List<String> failedsMessages = new ArrayList<String>();
		failedsMessages.add("[aPattern] não dá match em [matchingTarget]");
		failedsMessages.add("[aPattern1] não dá match em [matchingTarget1]");
		
		Faileds faileds = new Faileds();
		faileds.addOnlyJudgedFailed(new Failed(failedsMessages.get(0)));
		faileds.addOnlyJudgedFailed(new Failed(failedsMessages.get(1)));
		
		assertEquals(Joiner.on("<br>").join(failedsMessages), faileds.getReason());
	}

}
