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

}