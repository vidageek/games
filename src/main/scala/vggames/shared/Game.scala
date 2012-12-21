package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription
import vggames.shared.task.Tasks
import vggames.shared.task.IndexedTask

trait Game {

  val tasks : Tasks

  def getDescription : String

  def getName : String

  def task(index : Int) : TaskWithDescription[_] = tasks.at(index)

  def getSize : Int = tasks.size

  def getTasks : Collection[_ <: TaskWithDescription[_]] = tasks.all

  def allTasks = tasks.tasks

  def advance(index : Int)(f : Int => Unit) = if (hasNextTask(index)) f(nextTask(index))

  def atGroupEnd(index : Int)(f : => Unit) =
    if (!hasNextTask(index) || task(index).getGroupName != task(nextTask(index)).getGroupName) f

  def atEnd(index : Int)(f : => Unit) = if (!hasNextTask(index)) f

  def nextTask(index : Int) : Int = index + 1

  def hasNextTask(index : Int) : Boolean = nextTask(index) < getSize
}