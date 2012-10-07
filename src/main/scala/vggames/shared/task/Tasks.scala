package vggames.shared.task

import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.collection.mutable.ListBuffer

class Tasks(taskGroups : TaskGroup*) {

  val tasks : List[IndexedTask] = unmodifiableIndexedTasks

  def at(index : Int) = tasks(index)

  def size = tasks.size

  def all : java.util.List[IndexedTask] = tasks.asJava

  private def unmodifiableIndexedTasks = {
    val buffer = ListBuffer[IndexedTask]()
    var i = -1

    taskGroups.foreach(group =>
      group.foreach(task =>
        buffer += new IndexedTask(new GroupedTask(group, task), { i += 1; i })))

    buffer.toList
  }
}