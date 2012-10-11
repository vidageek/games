package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1

class TrimString extends GameSpecification[RestrictedFunction1[String, String]] {

  def runSignature = "(a:String):String"

  def extendsType = "RestrictedFunction1[String, String]"

  def getChallenge = """Remova os espaços no fim e no começo da string <code>a</code>"""

  "O seu código" should {
    """ remover os espaços de "villa " e devolver "villa" """ in {
      code("villa  ") must_== "villa"
    }

    """ remover os espaços de " viva" e devolver "viva" """ in {
      code(" viva") must_== "viva"
    }

    """ remover os espaços de "  lalala " e devolver "lalala" """ in {
      code("  lalala  ") must_== "lalala"
    }

    """ não mudar a string "abra" """ in {
      code("abra") must_== "abra"
    }

  }
}