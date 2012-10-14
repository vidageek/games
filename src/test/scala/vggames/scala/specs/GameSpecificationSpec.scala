package vggames.scala.specs

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.task.status.Ok
import vggames.scala.code.RestrictedFunction2
import org.specs2.main.ArgProperty
import vggames.scala.code.RestrictedFunction0

@RunWith(classOf[JUnitRunner])
class GameSpecificationSpec extends Specification {
  sequential

  "Sum judge" should {
    "returns ok if the code sums two values" in {
      new TestSpec("sum").judge("a + b") must_== Ok()
    }

    "returns a failure if the code does not sum two values" in {
      new TestSpec("div").judge("a / b").getReason must contain("spec-fail")
    }

    "returns a compilation failure if the code does not compile" in {
      new TestSpec("comp failure").judge("a + ").getReason must contain("Falha de compila&ccedil;&atilde;o: ")
    }

    "returns a exception failure if the code throws an exception" in {
      new TestSpec("exception").judge("1 / 0").getReason must startWith("Exception foi lan&ccedil;ada durante execu&ccedil;&atilde;o: ")
    }
  }

  "Simple Eval" should {
    "fail for unsafe code" in {
      val fail = new TestSpec("exit").judge("System.exit(-1);1")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "fail for file access" in {
      val fail = new TestSpec("file").judge("""new java.io.File(".").exists;1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "fail for web request attempt" in {
      val fail = new TestSpec("conn").judge("""new java.net.URL("http://www.google.com.br/").openConnection;1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "fail for classloader creation" in {
      val fail = new TestSpec("classloader").judge("""new java.net.URLClassLoader(Array[java.net.URL]());1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "fail for security manager modification attempt" in {
      val fail = new TestSpec("sec manager").judge("""System.setSecurityManager(null);1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "fail for exception catching attempt" in {
      val fail = new TestSpec("catch").judge("""try {} catch {case _ => };1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "fail for finally (can be used to stop exception propagation)" in {
      val fail = new TestSpec("finally").judge("""try {} finally {return ""};1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "fail for attempt to allow task to run priviledged code" in {
      val fail = new TestSpec("finally").judge("""TaskRunSecurityManager.unsafe.set(false);1""")
      fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

    "timeout and fail for infinite loops" in {
      val fail = new TestSpec("while").judge("""while(true){Thread.sleep(500)};1""")
      fail.getReason must contain("Exceeded max compilation and run time.")
    }
  }

  "multiple assert spec" should {
    "fail if first assert fails" in {
      val fail = new MultipleAssertSpec().judge("")
      fail.getReason must contain("spec-fail")
    }
  }
}

class TestSpec(c : String) extends GameSpecification[RestrictedFunction2[Int, Int, Int]] {
  def runSignature = "(a:Int, b:Int):Int"
  def extendsType = "RestrictedFunction2[Int, Int, Int]"
  def getChallenge = c

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "a" should {
      "b" in { code(1, 2) must_== 3 }
      "c" in { code(2, 3) must_== 5 }
    }
}

class MultipleAssertSpec extends GameSpecification[RestrictedFunction0[Unit]] {

  def runSignature = ":Unit"
  def extendsType = "RestrictedFunction0[Unit]"
  def getChallenge = "multiple"

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "a" should {
      "b" in {
        1 must_== 2
        2 must_== 2
      }
    }

}