package vggames.scala

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaProcessorSpec extends Specification {
  "code processor" should {
    "return true for a simple true boolean expression" in {
      new ScalaProcessor().processCode[Boolean]("true") must_== true
    }
  }
}
