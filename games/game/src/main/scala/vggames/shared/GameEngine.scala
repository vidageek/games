package vggames.shared

import vggames.shared.task.Tasks

trait GameEngine {

  val tasks : Tasks

  def description : String

  def name : String

  def path : String

  def resourceDescription : Option[ResourceDescription] = None

}
