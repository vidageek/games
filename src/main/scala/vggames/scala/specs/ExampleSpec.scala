package vggames.scala.specs

import org.specs2.mutable.Specification

class ExampleSpec extends Specification {

  "a example spec" should {
    "run against user code" in {
      new UserCode().apply(1, 2) must_== 3
    }

    "run against user code2" in {
      new UserCode().apply(1, 2) must_== 2
    }
  }

}

class UserCode {
  def apply(a : Int, b : Int) = a + b
}