package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import vggames.regex.Escaper;
import vggames.regex.Escaper

@RunWith(classOf[JUnitRunner])
class EscaperSpec extends Specification {
  "the escaper" should {
    "transform space to space demarcation" in {
      new Escaper().apply(" ") must_== "-Espa&ccedil;o-"
    }

    "transform empty string to empty demarcation" in {
      new Escaper().apply("") must_== "-Vazio-"
    }

    "transform new line to new line demarcation" in {
      new Escaper().apply("\n") must_== "-Quebra-de-Linha-"
    }

    "transform tab to tab demarcation" in {
      new Escaper().apply("\t") must_== "-Tab-"
    }
    "transform return to return demarcation" in {
      new Escaper().apply("\r") must_== "-Retorno-"
    }
  }
}
