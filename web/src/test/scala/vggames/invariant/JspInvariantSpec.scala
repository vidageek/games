package vggames.invariant

import net.vidageek.invariant._
import org.junit.Assert._
import org.junit.Test
import org.junit.runner.RunWith
import net.vidageek.invariant.Invariant
import net.vidageek.invariant.InvariantRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(classOf[InvariantRunner])
class JspInvariantTest {

  @Test
  @Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/WEB-INF/jsp")
  def tagsScriptNaoSaoPermitidasEmJsps(data : FileData) {
    assertFalse(data.getContent().contains("<script"))
  }

  @Test
  @Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/WEB-INF/jsp")
  def tagsLinkNaoSaoPermitidasEmJsps(data : FileData) {
    assertFalse(data.getContent().contains("<link"))
  }

  @Test
  @Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/")
  def tagsCssNaoSaoPermitidas(data : FileData) {
    assertFalse("Use <aw:css /> no lugar", data.getContent().contains("<link"))
  }

  @Test
  @Invariant(affects = ".*\\.jsp", folder = "src/main/webapp/")
  def tagsImgNaoSaoPermitidas(data : FileData) {
    assertFalse("Use <aw:img /> no lugar", data.getContent().contains("<img"))
  }
}
