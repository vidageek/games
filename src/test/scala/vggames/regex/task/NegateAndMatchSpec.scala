package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.regex.task.MatcherTargets.from
import vggames.shared.task.status.Failed

@RunWith(classOf[JUnitRunner])
class NegateAndMatchSpec extends Specification {
  "a negate and match" should {
    val cannotMatch = from("a", "b")
    val shouldMatch = from("c", "d")
    val aNegateCharClassTask = new NegateAndMatch(cannotMatch, shouldMatch)

    "return failed when match" in {
      val judge = aNegateCharClassTask.judge("[^ef]")
      judge must beAnInstanceOf[Failed]
      judge.getReason must_== "N&atilde;o deveria fazer match com <code>a</code><br />N&atilde;o deveria fazer match com <code>b</code>"
    }

    "show challenge with what should match and what cannot match" in {
      aNegateCharClassTask.getChallenge must_== "Qual RegEx n&atilde;o reconhece <code>a</code> e <code>b</code> mas reconhece <code>c</code> e <code>d</code>?"
    }
  }
}
