package net.vidageek.games.regex.invariant;

import static org.junit.Assert.assertFalse;
import net.vidageek.invariant.FileData;
import net.vidageek.invariant.Invariant;
import net.vidageek.invariant.InvariantRunner;

import org.junit.runner.RunWith;

@RunWith(InvariantRunner.class)
final public class JspInvariantTest {

	@Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/WEB-INF/jsp")
	public void tagsScriptNaoSaoPermitidasEmJsps(final FileData data) {
		assertFalse(data.getContent().contains("<script"));
	}
	
	@Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/")
	public void tagsCssNaoSaoPermitidas(final FileData data) {
		assertFalse("Use <aw:css /> no lugar", data.getContent().contains("<css"));
	}
	
	@Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/")
	public void tagsImgNaoSaoPermitidas(final FileData data) {
		assertFalse("Use <aw:img /> no lugar", data.getContent().contains("<img"));
	}
	
	@Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/")
	public void tagsJavascriptNaoSaoPermitidas(final FileData data) {
		assertFalse("Use <aw:js /> no lugar", data.getContent().contains("<script"));
	}
}
