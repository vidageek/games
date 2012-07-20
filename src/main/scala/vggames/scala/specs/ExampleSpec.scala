package vggames.scala.specs

class ExampleSpec extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {

  def runSignature = "(a:Int, b:Int):Int"

  def extendsType = "RestrictedFunction2[Int, Int, Int]"

  def challenge = "Deve somar duas variáveis <code>a</code> e <code>b</code>"

  "O seu código" should {
    "1 + 2 == 3" in {
      code(1, 2) must_== 3
    }

    "1 + 2 == 2" in {
      code(1, 2) must_== 2
    }
  }

}
