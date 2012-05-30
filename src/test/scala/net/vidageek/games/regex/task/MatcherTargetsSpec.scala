package net.vidageek.games.regex.task

import net.vidageek.games.regex.task.MatcherTargets.from
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MatcherTargetsSpec extends Specification {
  "matcher targets" should {
    "interate over matcher targets" in {
      from("a", "b") must contain("a", "b").only
    }

    "build good html for 1 target" in {
      from("A").asHtml must_== "<code>A</code>"
    }

    "build good html for 2 targets" in {
      from("A", "B").asHtml must_== "<code>A</code> e <code>B</code>"
    }

    "build good html for 3 targets" in {
      from("a", "b", "c").asHtml must_== "<code>a</code>, <code>b</code> e <code>c</code>"
    }
  }
}
