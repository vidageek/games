package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription
import vggames.shared.task.Descriptions
import vggames.shared.task.Descriptions
import vggames.shared.task.TaskGroup

class Game(engine : GameEngine, descriptions : Descriptions) {

  def task(index : Int) : TaskWithDescription = engine.tasks.at(index, descriptions)

  def tasks = engine.tasks.all(descriptions)

  def groups = tasks.map(t => Group(t.group, t.index, t.description)).distinct

  def size : Int = engine.tasks.size

  final def getSize = size

  def description = engine.description

  final def name = engine.name

  def getName = name

  def path = engine.path

  def resourceDescription : Option[ResourceDescription] = engine.resourceDescription

  def advance(index : Int)(f : Int => Unit) = if (hasNextTask(index)) f(nextTask(index))

  def atGroupEnd(index : Int)(f : => Unit) =
    if (!hasNextTask(index) || task(index).groupName != task(nextTask(index)).groupName) f

  def atEnd(index : Int)(f : => Unit) = if (!hasNextTask(index)) f

  def nextTask(index : Int) : Int = index + 1

  def hasNextTask(index : Int) : Boolean = nextTask(index) < size
}

case class Group(taskGroup : TaskGroup, index : Int, description : String) {
  override def equals(o : Any) = taskGroup.equals(o.asInstanceOf[Group].taskGroup)
  override def hashCode = taskGroup.hashCode
}
