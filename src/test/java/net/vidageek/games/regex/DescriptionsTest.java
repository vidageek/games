package net.vidageek.games.regex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

final public class DescriptionsTest {

	@Test
	public void shouldReadDescriptionAccordingToGroupName() {
		assertEquals("test <br />", new Descriptions().forGroup("test"));
	}

	@Test
	public void shouldReturnNoDescriptionForGroupWhenGroupHasNoDescription() {
		assertEquals("No description for group test.not.exists", new Descriptions().forGroup("test.not.exists"));
	}

	@Test
	public void shouldCacheDescriptionToAvoidLookup() {
		final Descriptions descriptions = new Descriptions();
		assertSame(descriptions.forGroup("test"), descriptions.forGroup("test"));
		assertSame(descriptions.forGroup("test"), descriptions.forGroup("test"));
		assertSame(descriptions.forGroup("test"), descriptions.forGroup("test"));
	}

}
