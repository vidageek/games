package vggames.scala.specs.booleans

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction2
import vggames.scala.specs.TestRun

class MoreThan extends GameSpecification[RestrictedFunction2[Int, Int, Boolean]] {

  def runSignature = "(a:Int, b:Int):Boolean"

  def extendsType = "RestrictedFunction2[Int, Int, Boolean]"

  def getChallenge = """Devolva <code>true</code> quando <code>a</code> for maior que <code>b</code>"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ devolver true para 2 e 1""" in {
        code(2, 1) must beTrue
      }

      """ devolver true para 10 e 2""" in {
        code(10, 2) must beTrue
      }

      """ devolver false para 2 e 3""" in {
        code(2, 3) must beFalse
      }

      """ devolver false para 3 e 3""" in {
        code(3, 3) must beFalse
      }
    }
}