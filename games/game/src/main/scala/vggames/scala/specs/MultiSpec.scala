package vggames.scala.specs

import vggames.scala.code.RestrictedFunction2

class MultiSpec extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {

  def runSignature = "(a:Int, b:Int):Int"

  def extendsType = "RestrictedFunction2[Int, Int, Int]"

  def getChallenge = "Multiplique duas vari&aacute;veis <code>a</code> e <code>b</code>"

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      "multiplicar a e b e resultar em 3 quando a = 1 e b = 3" in {
        code(1, 3) must_== 3
      }

      "multiplicar a e b e resultar em 6 quando a = 2 e b = 3" in {
        code(2, 3) must_== 6
      }
    }

}
