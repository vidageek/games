package vggames.shared.task

import java.util.ArrayList
import java.util.Collections
import scala.collection.JavaConversions.asScalaIterator

class TaskGroup(name : String, groupName : String, descriptions : Descriptions) extends Iterable[Task] {
  val tasks = new ArrayList[Task]

  def add(task : Task) : Unit = tasks.add(task)

  override def size = tasks.size

  def task(index : Int) = tasks.get(index)

  def iterator = Collections.unmodifiableCollection(tasks).iterator

  def getDescription = descriptions.forGroup(groupName)

  def getName = name
}