package vggames.shared.task

import java.util.ArrayList
import java.util.Collections
import scala.collection.JavaConversions.asScalaIterator

class TaskGroup(name : String, groupName : String, descriptions : Descriptions, tasks : Task*) extends Iterable[Task] {

  override def size = tasks.size

  def task(index : Int) = tasks(index)

  def iterator = tasks.iterator

  def getDescription = descriptions.forGroup(groupName)

  def getName = name
}