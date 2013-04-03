package vggames.scala.specs.functions

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class BasicFunction extends GameSpecification[RestrictedFunction0[Int => Int]] {

  def runSignature = "():Int=>Int"

  def extendsType = "RestrictedFunction0[Int=>Int]"

  def getChallenge = """Crie uma fun&ccedil;&atilde;o que receba um n&uacute;mero e o incremente."""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu codigo" should {

      """ incluir uma soma""" in {
        val soma = """\s*[A-Za-z0-9-]\s*\+\s*[A-Za-z0-9-]\s*""".r
        soma.findFirstIn(submittedCode).isDefined must beTrue
      }
    
      """ incrementar o valor """ in {
        code()(1) must_== 2
        code()(2) must_== 3
      }

    }

}