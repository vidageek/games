package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.regex.MatcherTargets

@RunWith(classOf[JUnitRunner])
class MatcherTargetsSpec extends Specification {
  "matcher targets" should {
    "interate over matcher targets" in {
      new MatcherTargets(List("a", "b")) must contain("a", "b").only
    }

    "build good html for 1 target" in {
      new MatcherTargets(List("A")).asHtml must_== "<code>A</code>"
    }

    "build good html for 2 targets" in {
      new MatcherTargets(List("A", "B")).asHtml must_== "<code>A</code> e <code>B</code>"
    }

    "build good html for 3 targets" in {
      new MatcherTargets(List("a", "b", "c")).asHtml must_== "<code>a</code>, <code>b</code> e <code>c</code>"
    }
  }
}
