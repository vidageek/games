package net.vidageek.games.regex.task

import net.vidageek.games.task.status.Failed
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CaptureGroupSpec extends Specification {
  "a capture group" should {
    "capture a single group" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("([a-z]+)")
      judge.getOk() must beTrue
    }

    "match group one target" in {
      val judge = new CaptureGroup("abcdef1a", "abcdef").judge("([a-z]+).*")
      judge.getOk() must beTrue
    }

    "match all groups target" in {
      val judge = new CaptureGroup("abcdef1a", "abcdef", "1a").judge("([a-z]+)(.*)")
      judge.getOk() must beTrue
    }

    "contain a capture group" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("[a-z]+")
      judge.getOk() must beFalse
      judge must beAnInstanceOf[Failed]
    }

    "fail if does not match" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("(\\d+)")
      judge.getOk() must beFalse
      judge must beAnInstanceOf[Failed]
    }

    "fail if group 0 is not the matching target" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("([a-d]+)")
      judge.getOk() must beFalse
      judge must beAnInstanceOf[Failed]
    }

    "fail if group 1 is not the matching target" in {
      val judge = new CaptureGroup("abcdef", "abcdef").judge("([a-d]+)ef")
      judge.getOk() must beFalse
      judge must beAnInstanceOf[Failed]
    }
  }
}
