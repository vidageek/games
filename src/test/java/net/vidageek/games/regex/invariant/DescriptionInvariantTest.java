package net.vidageek.games.regex.invariant;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import org.junit.Test;

final public class DescriptionInvariantTest {

	private static final String ALLOWED_CHARS = "[\\w\\s\\Q<>&/.,();+=^:\\?[]-${}|*\\E]*";

	@Test
	public void allDescriptionsMustUseHtmlEntities() throws Throwable {
		URL url = DescriptionInvariantTest.class.getResource("/desc/match.capture.html");
		for (File file : new File(url.toString().substring("file:".length()).replace("match.capture.html", ""))
				.listFiles()) {
			if (file.getAbsolutePath().endsWith("html")) {
				String content = new Scanner(file).useDelimiter("$$").next();
				assertTrue(	"File " + file.getAbsolutePath() + " contains invalid chars: ["
									+ content.replaceAll(ALLOWED_CHARS, "") + "]", containsInvalidChars(content));
			}
		}
	}

	private boolean containsInvalidChars(final String content) throws Throwable {
		return content.matches(ALLOWED_CHARS);
	}
}
