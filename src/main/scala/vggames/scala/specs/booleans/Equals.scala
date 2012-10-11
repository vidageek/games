package vggames.scala.specs.booleans

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction2

class Equals extends GameSpecification[RestrictedFunction2[Int, Int, Boolean]] {

  def runSignature = "(a:Int, b:Int):Boolean"

  def extendsType = "RestrictedFunction2[Int, Int, Boolean]"

  def getChallenge = """Devolva <code>true</code> quando <code>a</code> e <code>b</code> forem iguais"""

  "O seu código" should {
    """ devolver true para 1 e 1""" in {
      code(1, 1) must beTrue
    }

    """ devolver true para 2 e 2""" in {
      code(2, 2) must beTrue
    }

    """ devolver false para 2 e 3""" in {
      code(2, 3) must beFalse
    }
  }
}