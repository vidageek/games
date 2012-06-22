package vggames.shared

import java.util.Collection
import vggames.shared.task.TaskWithDescription
import vggames.regex.task.Tasks

trait Game {

  val tasks : Tasks

  def getDescription : String

  def getName : String

  def task(index : Int) : TaskWithDescription = tasks.at(index)

  def getSize : Int = tasks.size

  def getTasks : Collection[_ <: TaskWithDescription] = tasks.all

  def hasNextTask(index : Int) : Boolean = nextTask(index) < getSize

  def nextTask(index : Int) : Int = index + 1

}