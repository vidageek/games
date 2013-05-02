package vggames.scala.specs.literal

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class SumTwoNumbers extends GameSpecification[RestrictedFunction0[Int]] {

  def runSignature = "():Int"

  def extendsType = "RestrictedFunction0[Int]"

  def getChallenge = """Some os números 1 e 2"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código " should {
      "Somar os números 1 e 2 e produzir 3" in {
        code() must_== 3
      }
      "Conter o número 1" in {
        submittedCode must contain("1")
      }
      "Conter o número 2" in {
        submittedCode must contain("2")
      }
      "Conter o sinal de soma" in {
        submittedCode must contain("+")
      }
    }
}