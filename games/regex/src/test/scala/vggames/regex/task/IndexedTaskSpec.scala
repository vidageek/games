package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.task.Exercise
import vggames.shared.task.status.Error
import vggames.shared.task.TaskGroup
import vggames.regex.Match

@RunWith(classOf[JUnitRunner])
class IndexedTaskSpec extends Specification {
  "an IndexedTask" should {
    "return judged task with error when invalid regex" in {
      new Exercise(new TaskGroup("b", "", null), null, new Match("a"), 1).judge("aIncalidRegex)") must beAnInstanceOf[Error]
    }
  }
}
