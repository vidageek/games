package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.Descriptions

@RunWith(classOf[JUnitRunner])
class GroupedTaskSpec extends Specification with Mockito {
  "a grouped task" should {
    val descriptions = mock[Descriptions]

    "return group description" in {
      descriptions.forGroup("a") returns "description"

      val task = new GroupedTask(new TaskGroup("b", "a", descriptions), new TestTask())
      task.getDescription must_== "description"
    }
  }
}
