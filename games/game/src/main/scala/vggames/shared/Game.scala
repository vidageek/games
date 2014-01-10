package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription
import vggames.shared.task.Descriptions
import vggames.shared.task.Descriptions

class Game(engine : GameEngine, descriptions : Descriptions) {

  def task(index : Int) : TaskWithDescription[_] = engine.tasks.at(index, descriptions)

  def getTasks : Collection[_ <: TaskWithDescription[_]] = engine.tasks.all(descriptions)

  def getSize : Int = engine.tasks.size

  def getDescription = engine.getDescription

  def getName = engine.getName

  def resourceDescription : Option[ResourceDescription] = engine.resourceDescription

  def advance(index : Int)(f : Int => Unit) = if (hasNextTask(index)) f(nextTask(index))

  def atGroupEnd(index : Int)(f : => Unit) =
    if (!hasNextTask(index) || task(index).getGroupName != task(nextTask(index)).getGroupName) f

  def atEnd(index : Int)(f : => Unit) = if (!hasNextTask(index)) f

  def nextTask(index : Int) : Int = index + 1

  def hasNextTask(index : Int) : Boolean = nextTask(index) < getSize

}