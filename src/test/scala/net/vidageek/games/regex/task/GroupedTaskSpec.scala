package net.vidageek.games.regex.task

import net.vidageek.games.regex.Descriptions
import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GroupedTaskSpec extends Specification with Mockito {
  "a grouped task" should {
    val descriptions = mock[Descriptions]
    
    "return group description" in {
      descriptions.forGroup("a") returns "description"

      val task = new GroupedTask(new TaskGroup("b", "a", descriptions), new TestTask())
      task.getDescription() must_== "description"
    }
  }
}
