package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import vggames.regex.CaptureGroup;
import vggames.shared.task.status.Failed
import vggames.regex.CaptureGroup

@RunWith(classOf[JUnitRunner])
class CaptureGroupSpec extends Specification {
  "a capture group" should {
    "capture a single group" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("([a-z]+)")
      judge.ok must beTrue
    }

    "match group one target" in {
      val judge = new CaptureGroup("abcdef1a", "abcdef").judge("([a-z]+).*")
      judge.ok must beTrue
    }

    "match all groups target" in {
      val judge = new CaptureGroup("abcdef1a", "abcdef", "1a").judge("([a-z]+)(.*)")
      judge.ok must beTrue
    }

    "contain a capture group" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("[a-z]+")
      judge.ok must beFalse
      judge must beAnInstanceOf[Failed]
    }

    "fail if does not match" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("(\\d+)")
      judge.ok must beFalse
      judge must beAnInstanceOf[Failed]
    }

    "fail if group 0 is not the matching target" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("([a-d]+)")
      judge.ok must beFalse
      judge must beAnInstanceOf[Failed]
    }

    "fail if group 1 is not the matching target" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("([a-d]+)ef")
      judge.ok must beFalse
      judge must beAnInstanceOf[Failed]
    }
  }
}
