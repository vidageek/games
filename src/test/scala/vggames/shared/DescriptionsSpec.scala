package vggames.shared

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.task.Descriptions

@RunWith(classOf[JUnitRunner])
class DescriptionsSpec extends Specification {

  "descriptions" should {
    "read description according to group name" in {
      new Descriptions("asdrubal").forGroup("test") must_== "<p>test <br /></p>\n"
    }

    "return no description for group when group has no description" in {
      new Descriptions("asdrubal").forGroup("test.not.exists") must_== "No description for group test.not.exists"
    }

    "cache description to avoid lookup" in {
      val descriptions = new Descriptions("asdrubal")
      descriptions.forGroup("test") must_== descriptions.forGroup("test")
      descriptions.forGroup("test") must_== descriptions.forGroup("test")
      descriptions.forGroup("test") must_== descriptions.forGroup("test")
    }

    "compile markdown syntax" in {
      new Descriptions("asdrubal").forGroup("markdown") must_== "<h1>abc</h1>\n<p>cde</p>\n"
    }
  }
}
