package vggames.shared.task

import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.collection.mutable.ListBuffer

class Tasks(taskGroups : TaskGroup*) {

  val tasks : List[IndexedTask[_]] = unmodifiableIndexedTasks

  def at(index : Int, descriptions : Descriptions) : TaskWithDescription[_] = new TaskWithDescription(tasks(index), descriptions)

  def size = tasks.size

  def all(descriptions : Descriptions) : java.util.List[_ <: TaskWithDescription[_]] = tasks.map(new TaskWithDescription(_, descriptions)).asJava

  private def unmodifiableIndexedTasks = {
    taskGroups.foldLeft((0, List[IndexedTask[_]]())) { (acc, group) =>
      group.foldLeft(acc) { (acc, task) =>
        (acc._1 + 1, acc._2 :+ new IndexedTask(new GroupedTask(group, task), acc._1))
      }
    }._2
  }
}