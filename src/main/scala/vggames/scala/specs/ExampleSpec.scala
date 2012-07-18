package vggames.scala.specs

class ExampleSpec extends GameSpecification[(Int, Int) => Int] {

  def challenge = ""

  def wrap(className : String, code : String) = {
    "package scalagameunsafe\n" +
      "class " + className + " extends ((Int, Int) => Int) {\n" +
      "  def apply(a:Int, b:Int):Int = {\n" +
      code + "\n" +
      "  }\n" +
      "}\n"
  }

  "a example spec" should {
    "run against user code" in {
      code(1, 2) must_== 3
    }

    "run against user code2" in {
      code(1, 2) must_== 2
    }
  }

}
