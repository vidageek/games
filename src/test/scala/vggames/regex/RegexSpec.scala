package vggames.regex

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import vggames.regex.task.Regex

@RunWith(classOf[JUnitRunner])
class RegexTest extends Specification {

  "regex" should {
    "find a group" in {
      val matchAtGroup = "aaaa"
      new Regex("b+([a]+)c+").group(1).from("bbb" + matchAtGroup + "c") must_== matchAtGroup
    }

    "throw an IlegalArgumentException when group doesn't match" in {
      new Regex("b+([a]+)c+").group(1).from("bbbc") must throwAn[IllegalArgumentException](
        message = "could not match text.")
    }
  }
}
