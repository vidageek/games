package vggames.regex.task

import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.collection.mutable.ListBuffer
import vggames.shared.task.IndexedTask
import vggames.shared.task.GroupedTask

class Tasks {
  val taskGroups = ListBuffer[TaskGroup]()

  def add(group : TaskGroup) : Unit = taskGroups += group

  def at(originalIndex : Int) = {
    var index = originalIndex
    var groupCount = 0
    while ((groupCount < taskGroups.size) && (index - taskGroups(groupCount).size >= 0)) {
      index -= taskGroups(groupCount).size
      groupCount += 1
    }
    val group = taskGroups(groupCount)
    new IndexedTask(new GroupedTask(group, group.task(index)), originalIndex)
  }

  def size = taskGroups.foldLeft(0)(_ + _.size)

  def all : java.util.List[IndexedTask] = unmodifiableIndexedTasks.asJava

  private def unmodifiableIndexedTasks = {
    val list = ListBuffer[IndexedTask]()
    var i = -1
    taskGroups.foreach(group =>
      group.foreach(task =>
        list += new IndexedTask(new GroupedTask(group, task), { i += 1; i })))

    list.toList
  }
}