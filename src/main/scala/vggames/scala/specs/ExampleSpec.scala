package vggames.scala.specs

class ExampleSpec extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {

  def challenge = "Deve somar duas variáveis <code>a</code> e <code>b</code>"

  def wrap(className : String, code : String) = {
    "package scalagameunsafe\n" +
      "import vggames.scala.specs._\n" +
      "class " + className + " extends RestrictedFunction2[Int, Int, Int] {\n" +
      "  def run(a:Int, b:Int):Int = {\n" +
      code + "\n" +
      "  }\n" +
      "}\n"
  }

  "O seu código" should {
    "1 + 2 == 3" in {
      code(1, 2) must_== 3
    }

    "1 + 2 == 2" in {
      code(1, 2) must_== 2
    }
  }

}
