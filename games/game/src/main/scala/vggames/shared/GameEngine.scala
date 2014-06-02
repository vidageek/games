package vggames.shared

import vggames.shared.task.Tasks

trait GameEngine {

  val tasks : Tasks

  def description : String

  def path : String

  def name : String = path.capitalize

  def resourceDescription : Option[ResourceDescription] = None

}
