package vggames.scala.specs.literal

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class MultiplyTwoNumbers extends GameSpecification[RestrictedFunction0[Int]] {

  def runSignature = "():Int"

  def extendsType = "RestrictedFunction0[Int]"

  def challenge = """Multiplique os números 2 e 3"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código " should {
      "Multiplicar os números 2 e 3 e produzir 6" in {
        code() must_== 6
      }
      "Conter o número 2" in {
        submittedCode must contain("2")
      }
      "Conter o número 3" in {
        submittedCode must contain("3")
      }
      "Conter o sinal de multiplicação" in {
        submittedCode must contain("*")
      }
    }
}