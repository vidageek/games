package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1

class AnyRefToString extends GameSpecification[RestrictedFunction1[Any, String]] {

  def runSignature = "(a:Any):String"

  def extendsType = "RestrictedFunction1[Any, String]"

  def getChallenge = """Transforme <code>a</code> em string"""

  "O seu código" should {
    """ transformar 1 em string""" in {
      code(1) must_== "1"
    }
    """ transformar "arara" em string""" in {
      code("arara") must_== "arara"
    }
    """ transformar objeto em string""" in {
      code(Objeto) must_== Objeto.toString
    }
  }
}

object Objeto