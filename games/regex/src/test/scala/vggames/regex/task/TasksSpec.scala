package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

@RunWith(classOf[JUnitRunner])
class TasksSpec extends Specification {
  "Tasks" should {
    val tasks = new Tasks(
      new TaskGroup("b", "",
        new TestTask(),
        new TestTask()),
      new TaskGroup("b", "",
        new TestTask(),
        new TestTask()),
      new TaskGroup("b", "",
        new TestTask(),
        new TestTask()))

    "all tasks must obey indexing" in {
      0 until 6 map { i =>
        tasks.at(i, null).getIndex must_== i
      }
    }

    "list all tasks should obey indexing" in {
      val all = tasks.all(null)
      0 until 6 map { i =>
        tasks.at(i, null).getIndex must_== i
      }
    }
  }
}
