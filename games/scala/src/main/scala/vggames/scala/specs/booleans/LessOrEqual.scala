package vggames.scala.specs.booleans

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction2
import vggames.scala.specs.TestRun

class LessOrEqual extends GameSpecification[RestrictedFunction2[Int, Int, Boolean]] {

  def runSignature = "(a:Int, b:Int):Boolean"

  def extendsType = "RestrictedFunction2[Int, Int, Boolean]"

  def challenge = """Devolva <code>true</code> quando <code>a</code> for menor ou igual a<code>b</code>"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ devolver true para 1 e 2""" in {
        code(1, 2) must beTrue
      }

      """ devolver true para 2 e 10""" in {
        code(2, 10) must beTrue
      }

      """ devolver true para 2 e 2""" in {
        code(2, 2) must beTrue
      }

      """ devolver false para 3 e 2""" in {
        code(3, 2) must beFalse
      }
    }
}