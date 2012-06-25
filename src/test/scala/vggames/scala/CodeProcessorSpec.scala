package vggames.scala

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CodeProcessorSpec extends Specification {
  "code processor" should {
    "return true for a simple true boolean expression" in {
      new CodeProcessor().processCode("true") must_== true
    }
  }
}
