package net.vidageek.games.regex.invariant;

import static org.junit.Assert.assertFalse;
import net.vidageek.invariant.FileData;
import net.vidageek.invariant.Invariant;
import net.vidageek.invariant.InvariantRunner;

import org.junit.runner.RunWith;

@RunWith(InvariantRunner.class)
final public class JspInvariantTest {

	@Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/WEB-INF/jsp")
	public void tagsScriptNaoSaoPermitidasEmJspsNaoDecorados(final FileData data) {
		assertFalse(data.getContent().contains("<script"));
	}
}
