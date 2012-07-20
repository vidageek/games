package vggames.regex.task

import vggames.regex.task.MatcherTargets._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.task.IndexedTask
import vggames.shared.task.Match
import vggames.shared.task.status.Error
import vggames.shared.task.GroupedTask
import vggames.shared.task.TaskGroup

@RunWith(classOf[JUnitRunner])
class IndexedTaskSpec extends Specification {
  "an IndexedTask" should {
    "return judged task with error when invalid regex" in {
      new IndexedTask(
        new GroupedTask(new TaskGroup("b", "", null), new Match(from("a"))), 1).judge("aIncalidRegex)") must beAnInstanceOf[Error]
    }
  }
}
