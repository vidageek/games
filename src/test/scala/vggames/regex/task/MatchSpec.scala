package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import vggames.regex.task.MatcherTargets.from
import vggames.shared.task.status.{Ok, Failed}
import vggames.shared.task.Match

@RunWith(classOf[JUnitRunner])
class MatchSpec extends Specification {
  "a match" should {
    val taskWithMatchinTargestAAndB = new Match(from("A", "B"))

    "matcher with all answers" in {
      taskWithMatchinTargestAAndB.judge("[AB]") must beAnInstanceOf[Ok]
    }

    "cannot match with 1 invalid chalenge answer" in {
      taskWithMatchinTargestAAndB.judge("[C]") must beAnInstanceOf[Failed]
    }

    "cannot match with 1 invalid and 1 valid chalenge answer" in {
      taskWithMatchinTargestAAndB.judge("[AC]") must beAnInstanceOf[Failed]
    }

    "show correct challenge" in {
      taskWithMatchinTargestAAndB.getChallenge() must_== "Qual RegEx reconhece <code>A</code> e <code>B</code>?"
    }

    "show correct challenge for 3 matching targets" in {
      new Match(from("a", "b", "c")).getChallenge() must_== "Qual RegEx reconhece <code>a</code>, <code>b</code> e <code>c</code>?"
    }

    "match all string" in {
      new Match(from("a")).judge(".").getOk() must beTrue
      new Match(from("aaabc")).judge(".+").getOk() must beTrue
    }

    "not match partial string" in {
      new Match(from("aa")).judge(".").getOk() must beFalse
      new Match(from("aaab\nc")).judge(".+").getOk() must beFalse
    }
  }
}
