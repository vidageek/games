package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription
import vggames.shared.task.Tasks

trait Game {

  val tasks : Tasks

  def getDescription : String

  def getName : String

  def task(index : Int) : TaskWithDescription = tasks.at(index)

  def getSize : Int = tasks.size

  def getTasks : Collection[_ <: TaskWithDescription] = tasks.all

  def canKeepPlaying(index : Int)(f : Int => Unit) = if (hasNextTask(index)) f(nextTask(index))

  def onEnd(index : Int)(f : => Unit) = if (!hasNextTask(index)) f

  private def nextTask(index : Int) : Int = index + 1

  private def hasNextTask(index : Int) : Boolean = nextTask(index) < getSize
}