package vggames.shared.task

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mock.Mockito

@RunWith(classOf[JUnitRunner])
class TaskGroupSpec extends Specification with Mockito {

  "task group" should {
    "parse name using markdown" in {
      new TaskGroup("**strong**", "group.name").getName must_==
        "<strong>strong</strong>"
    }
    "cache name value to avoid replaceAll" in {
      val group = new TaskGroup("**strong**", "group.name")
      group.getName eq (group.getName) must beTrue
    }
  }
}