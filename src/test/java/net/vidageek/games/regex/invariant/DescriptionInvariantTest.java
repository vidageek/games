package net.vidageek.games.regex.invariant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vidageek.invariant.FileData;
import net.vidageek.invariant.Invariant;
import net.vidageek.invariant.InvariantRunner;

import org.junit.runner.RunWith;

@RunWith(InvariantRunner.class)
final public class DescriptionInvariantTest {

	private static final String ALLOWED_CHARS = "[\\w\\s\\Q<>&/.,();+=^:\\?[]-${}|*\\E]*";

	@Invariant(affects = ".*\\.html", folder = "src/main/resources")
	public void allDescriptionsMustUseHtmlEntities(final FileData data) throws Throwable {
		final String content = data.getContent();
		assertFalse("Found invalid chars: [" + content.replaceAll(ALLOWED_CHARS, "") + "]",
					containsInvalidChars(content));
	}

	@Invariant(affects = ".*\\.html", folder = "src/main/resources")
	public void regexMustBeProperWritten(final FileData data) throws Throwable {
		Matcher matcher = Pattern.compile("(?i)(regex)").matcher(data.getContent());
		while (matcher.find()) {
			assertTrue("Found invalid way of writing RegEx", "RegEx".equals(matcher.group(1)));
		}
	}

	private boolean containsInvalidChars(final String content) throws Throwable {
		return !content.matches(ALLOWED_CHARS);
	}
}
