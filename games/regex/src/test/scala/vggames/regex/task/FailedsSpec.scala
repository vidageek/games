package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import vggames.shared.task.status.{ Faileds, Failed }

@RunWith(classOf[JUnitRunner])
class FailedsSpec extends Specification {
  "the Faileds" should {
    val faileds = new Faileds()
    faileds.addOnlyJudgedFailed(new Failed("[aPattern] não dá match em [matchingTarget]"))
    faileds.addOnlyJudgedFailed(new Failed("[aPattern1] não dá match em [matchingTarget1]"))

    "show description to any faileds" in {
      faileds.mkString("<br />") must_== faileds.reason
    }

    "show description to all faileds" in {
      val testFaileds = new Faileds()
      testFaileds.addAll(faileds)
      testFaileds.mkString("<br />") must_== testFaileds.reason
    }
  }
}
