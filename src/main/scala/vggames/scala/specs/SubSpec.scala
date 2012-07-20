package vggames.scala.specs

import vggames.scala.code.RestrictedFunction2

class SubSpec extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {

  def runSignature = "(a:Int, b:Int):Int"

  def extendsType = "RestrictedFunction2[Int, Int, Int]"

  def challenge = "Subtraia <code>a</code> de <code>b</code>"

  "O seu código" should {
    "subtrair a de b e resultar em 1 quando a = 2 e b = 3" in {
      code(2, 3) must_== 1
    }

    "subtrair a de b e resultar em 4 quando a = 1 e b = 5" in {
      code(1, 5) must_== 4
    }
  }

}
