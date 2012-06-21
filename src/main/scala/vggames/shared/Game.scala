package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription

trait Game {

  def getSize : Int

  def task(index : Int) : TaskWithDescription

  def getDescription : String

  def getTasks() : Collection[_ <: TaskWithDescription]

  def getName : String

  def hasNextTask(index : Int) : Boolean

  def nextTask(index : Int) : Int
}