package vggames.html

import org.specs2.mutable.Specification
import scala.util.Try
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TextToHtmlSpec extends Specification {

  "TextToHtml" should {
    "remove tags" in {
      TextToHtml.removeTags(Try("<h1>conteudo</h1>")) === Some("conteudo")
    }

    "keep href attribute on anchors" in {
      TextToHtml.removeTags(Try("""<a href="asdf">conteudo</a>""")) === Some("asdf conteudo")
    }

    "keep image attributes" in {
      TextToHtml.removeTags(Try("""<img src="asdf" alt="1234">conteudo</a>""")) === Some("asdf 1234 conteudo")
    }
  }

}