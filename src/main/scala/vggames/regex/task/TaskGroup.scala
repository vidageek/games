package vggames.regex.task

import java.util.{Collections, ArrayList}

import scala.collection.JavaConversions.asScalaIterator

import vggames.shared.task.{Task, Descriptions}

class TaskGroup(name : String, groupName : String, descriptions : Descriptions) extends Iterable[Task] {
  val tasks = new ArrayList[Task]

  def add(task : Task) : Unit = tasks.add(task)

  override def size = tasks.size

  def task(index : Int) = tasks.get(index)

  def iterator = Collections.unmodifiableCollection(tasks).iterator

  def getDescription = descriptions.forGroup(groupName)

  def getName = name
}