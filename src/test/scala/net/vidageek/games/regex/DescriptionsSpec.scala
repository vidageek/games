package net.vidageek.games.regex

import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class DescriptionsSpec extends Specification {

  "descriptions" should {
    "read description according to group name" in {
      "test <br />" must_== new Descriptions().forGroup("test")
    }

    "return no description for group when group has no description" in {
      "No description for group test.not.exists" must_== new Descriptions().forGroup("test.not.exists")
    }

    "cache description to avoid lookup" in {
      val descriptions = new Descriptions()
      descriptions.forGroup("test") must be(descriptions.forGroup("test"))
      descriptions.forGroup("test") must be(descriptions.forGroup("test"))
      descriptions.forGroup("test") must be(descriptions.forGroup("test"))
    }
  }
}
