package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1

class StringContains extends GameSpecification[RestrictedFunction1[String, Boolean]] {

  def runSignature = "(a:String):Boolean"

  def extendsType = "RestrictedFunction1[String, Boolean]"

  def getChallenge = """Devolva <code>true</code> se <code>a</code> contêm "ara" e <code>false</code> caso contrário"""

  "O seu código" should {
    """ devolver true para "arara" """ in {
      code("arara") must beTrue
    }

    """ devolver true para "cara" """ in {
      code("cara") must beTrue
    }
    """ devolver false para "cobra" """ in {
      code("cobra") must beFalse
    }

  }
}