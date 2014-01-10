package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription
import vggames.shared.task.Descriptions

class Game(engine : GameEngine) {

  def task(index : Int) : TaskWithDescription[_] = engine.tasks.at(index)

  def getTasks : Collection[_ <: TaskWithDescription[_]] = engine.tasks.all

  def getSize : Int = engine.tasks.size

  def allTasks = engine.tasks.tasks

  def resourceDescription : Option[ResourceDescription] = engine.resourceDescription

  def advance(index : Int)(f : Int => Unit) = if (hasNextTask(index)) f(nextTask(index))

  def atGroupEnd(index : Int)(f : => Unit) =
    if (!hasNextTask(index) || task(index).getGroupName != task(nextTask(index)).getGroupName) f

  def atEnd(index : Int)(f : => Unit) = if (!hasNextTask(index)) f

  def nextTask(index : Int) : Int = index + 1

  def hasNextTask(index : Int) : Boolean = nextTask(index) < getSize

}