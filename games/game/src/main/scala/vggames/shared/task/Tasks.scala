package vggames.shared.task

import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.collection.mutable.ListBuffer

class Tasks(taskGroups : TaskGroup*) {

  val tasks : List[IndexedTask] = unmodifiableIndexedTasks

  def at(index : Int, descriptions : Descriptions) : TaskWithDescription = new TaskWithDescription(tasks(index), descriptions)

  def size = tasks.size

  def all(descriptions : Descriptions) : List[_ <: TaskWithDescription] = tasks.map(new TaskWithDescription(_, descriptions))

  private def unmodifiableIndexedTasks = {
    taskGroups.foldLeft((0, List[IndexedTask]())) { (acc, group) =>
      group.foldLeft(acc) { (acc, task) =>
        (acc._1 + 1, acc._2 :+ new IndexedTask(group, task, acc._1))
      }
    }._2
  }
}