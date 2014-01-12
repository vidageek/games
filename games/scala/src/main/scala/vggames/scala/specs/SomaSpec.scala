package vggames.scala.specs

import vggames.scala.code.RestrictedFunction2

class SomaSpec extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {

  def runSignature = "(a:Int, b:Int):Int"

  def extendsType = "RestrictedFunction2[Int, Int, Int]"

  def challenge = "Some duas vari&aacute;veis <code>a</code> e <code>b</code>"

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      "somar a e b e resultar em 3 quando a = 1 e b = 2" in {
        code(1, 2) must_== 3
      }

      "somar a e b e resultar em 6 quando a = 2 e b = 4" in {
        code(2, 4) must_== 6
      }
    }

}