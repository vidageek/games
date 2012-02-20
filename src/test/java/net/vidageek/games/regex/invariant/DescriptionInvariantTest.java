package net.vidageek.games.regex.invariant;

import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vidageek.games.regex.invariant.infra.DescriptionInvariant;
import net.vidageek.games.regex.invariant.infra.Invariant;

import org.junit.Test;

final public class DescriptionInvariantTest {

	private static final String ALLOWED_CHARS = "[\\w\\s\\Q<>&/.,();+=^:\\?[]-${}|*\\E]*";

	@Test
	public void allDescriptionsMustUseHtmlEntities() throws Throwable {
		new DescriptionInvariant().run(new Invariant() {
			public void apply(final String content) throws Throwable {
				assertTrue(	" Found invalid chars: [" + content.replaceAll(ALLOWED_CHARS, "") + "]",
							containsInvalidChars(content));
			}
		});
	}

	@Test
	public void regexMustBeProperWritten() throws Throwable {
		new DescriptionInvariant().run(new Invariant() {
			public void apply(final String content) throws Throwable {
				Matcher matcher = Pattern.compile("(?i)(regex)").matcher(content);
				while (matcher.find()) {
					assertTrue("Found invalid way of writing RegEx", "RegEx".equals(matcher.group(1)));
				}
			}
		});
	}

	private boolean containsInvalidChars(final String content) throws Throwable {
		return content.matches(ALLOWED_CHARS);
	}
}
