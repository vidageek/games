package vggames.regex.task

import org.junit.runner.RunWith

import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import vggames.shared.task.status.{ Ok, Failed }
import vggames.regex.Match

@RunWith(classOf[JUnitRunner])
class MatchSpec extends Specification {
  "a match" should {
    val taskWithMatchinTargestAAndB = new Match(List("A", "B"))

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
      taskWithMatchinTargestAAndB.getChallenge must_== "Qual RegEx reconhece <code>A</code> e <code>B</code>?"
    }

    "show correct challenge for 3 matching targets" in {
      new Match(List("a", "b", "c")).getChallenge must_== "Qual RegEx reconhece <code>a</code>, <code>b</code> e <code>c</code>?"
    }

    "match all string" in {
      new Match("a").judge(".").getOk must beTrue
      new Match("aaabc").judge(".+").getOk must beTrue
    }

    "not match partial string" in {
      new Match("aa").judge(".").getOk must beFalse
      new Match("aaab\nc").judge(".+").getOk must beFalse
    }
  }
}
