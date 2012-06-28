package vggames.scala.tasks

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import vggames.shared.task.status.Ok

@RunWith(classOf[JUnitRunner])
class SumSpec extends Specification {

  "Sum judge" should {
    "returns ok if the code sums two values" in {
      (new Sum).judge("a + b") must_== Ok()
    }

    "returns a failure if the code does not sum two values" in {
      val fail = (new Sum).judge("a / b")
      fail.getReason must_== "Não somou dois números"
    }

    "returns a compilation failure if the code does not compile" in {
      val fail = (new Sum).judge("a +")
      fail.getReason must startWith("Falha de compilação: ")
    }

    "returns a exception failure if the code throws an exception" in {
      val fail = (new Sum).judge("1 / 0")
      fail.getReason must startWith("Exception foi lançada durante execução: ")
    }
  }

}