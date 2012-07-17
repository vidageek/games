package vggames.scala.tasks

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import vggames.shared.task.status.Ok

@RunWith(classOf[JUnitRunner])
class SimpleEvalSpec extends Specification {

  "Sum judge" should {
    "returns ok if the code sums two values" in {
      SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("a + b") must_== Ok()
    }

    "returns a failure if the code does not sum two values" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("a / b")
      fail.getReason must_== "N&atilde;o somou dois n&uacute;meros"
    }

    "returns a compilation failure if the code does not compile" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("a +")
      fail.getReason must contain("Falha de compila&ccedil;&atilde;o: ")
    }

    "returns a exception failure if the code throws an exception" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("1 / 0")
      fail.getReason must startWith("Exception foi lan&ccedil;ada durante execu&ccedil;&atilde;o: ")
    }
  }

  "Simple Eval" should {
    "throw exception for unsafe code" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("System.exit(-1)")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for file access" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("""new java.io.File(".").exists""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for web request attempt" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("""new java.net.URL("http://www.google.com.br/").openConnection""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for classloader creation" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("""new java.net.URLClassLoader(Array[java.net.URL]())""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for security manager modification attempt" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("""System.setSecurityManager(null)""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for exception catching attempt" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("""try {} catch {case _ => }""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "throw exception for finally (can be used to stop exception propagation)" in {
      val fail = SimpleEval("val a = 1;\nval b = 2;", 3, "").judge("""try {} finally {return ""}""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }
  }

}