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
      {
        val group = new TaskGroup("b", "", null)
        group.add(new TestTask())
        group.add(new TestTask())
        group
      }, {
        val group = new TaskGroup("b", "", null)
        group.add(new TestTask())
        group.add(new TestTask())
        group
      }, {
        val group = new TaskGroup("b", "", null)
        group.add(new TestTask())
        group.add(new TestTask())
        group
      })

    "all tasks must obey indexing" in {
      0 until 6 map { i =>
        tasks.at(i).getIndex must_== i
      }
    }

    "list all tasks should obey indexing" in {
      val all = tasks.all
      0 until 6 map { i =>
        tasks.at(i).getIndex must_== i
      }
    }
  }
}
