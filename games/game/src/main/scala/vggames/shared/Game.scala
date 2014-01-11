package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription
import vggames.shared.task.Descriptions
import vggames.shared.task.Descriptions
import vggames.shared.task.TaskGroup

class Game(engine : GameEngine, descriptions : Descriptions) {

  def task(index : Int) : TaskWithDescription = engine.tasks.at(index, descriptions)

  def tasks = engine.tasks.all(descriptions)

  def groups = tasks.map(t => Group(t.group, t.getIndex, t.getDescription)).distinct

  def getSize : Int = engine.tasks.size

  def getDescription = engine.getDescription

  def getName = engine.getName

  def path = engine.path

  def resourceDescription : Option[ResourceDescription] = engine.resourceDescription

  def advance(index : Int)(f : Int => Unit) = if (hasNextTask(index)) f(nextTask(index))

  def atGroupEnd(index : Int)(f : => Unit) =
    if (!hasNextTask(index) || task(index).getGroupName != task(nextTask(index)).getGroupName) f

  def atEnd(index : Int)(f : => Unit) = if (!hasNextTask(index)) f

  def nextTask(index : Int) : Int = index + 1

  def hasNextTask(index : Int) : Boolean = nextTask(index) < getSize
}

case class Group(taskGroup : TaskGroup, index : Int, description : String) {
  override def equals(o : Any) = taskGroup.equals(o.asInstanceOf[Group].taskGroup)
  override def hashCode = taskGroup.hashCode
}
