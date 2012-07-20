package vggames.scala.specs

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import vggames.shared.task.status.Ok

@RunWith(classOf[JUnitRunner])
class Specs2EvalSpec extends Specification {

  "Sum judge" should {
    "returns ok if the code sums two values" in {
      Specs2Eval(new TestSpec("sum")).judge("a + b") must_== Ok()
    }

    "returns a failure if the code does not sum two values" in {
      Specs2Eval(new TestSpec("div")).judge("a / b").getReason must contain("spec-fail")
    }

    "returns a compilation failure if the code does not compile" in {
      Specs2Eval(new TestSpec("comp failure")).judge("a + ").getReason must contain("Falha de compila&ccedil;&atilde;o: ")
    }

    "returns a exception failure if the code throws an exception" in {
      Specs2Eval(new TestSpec("exception")).judge("1 / 0").getReason must startWith("Exception foi lan&ccedil;ada durante execu&ccedil;&atilde;o: ")
    }
  }

  "Simple Eval" should {
    "throw exception for unsafe code" in {
      val fail = Specs2Eval(new TestSpec("exit")).judge("System.exit(-1);1")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for file access" in {
      val fail = Specs2Eval(new TestSpec("file")).judge("""new java.io.File(".").exists;1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for web request attempt" in {
      val fail = Specs2Eval(new TestSpec("conn")).judge("""new java.net.URL("http://www.google.com.br/").openConnection;1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for classloader creation" in {
      val fail = Specs2Eval(new TestSpec("classloader")).judge("""new java.net.URLClassLoader(Array[java.net.URL]());1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for security manager modification attempt" in {
      val fail = Specs2Eval(new TestSpec("sec manager")).judge("""System.setSecurityManager(null);1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for exception catching attempt" in {
      val fail = Specs2Eval(new TestSpec("catch")).judge("""try {} catch {case _ => };1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for finally (can be used to stop exception propagation)" in {
      val fail = Specs2Eval(new TestSpec("finally")).judge("""try {} finally {return ""};1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }
  }

  class TestSpec(c : String) extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {

    def challenge = c

    def wrap(className : String, code : String) = {
      "package scalagameunsafe\n" +
        "import vggames.scala.specs._\n" +
        "class " + className + " extends RestrictedFunction2[Int, Int, Int] {\n" +
        "  def run(a:Int, b:Int):Int = {\n" +
        code + "\n" +
        "  }\n" +
        "}\n"
    }

    "a" should {
      "b" in {
        code(1, 2) must_== 3
      }

      "c" in {
        code(2, 3) must_== 5
      }
    }
  }

}

