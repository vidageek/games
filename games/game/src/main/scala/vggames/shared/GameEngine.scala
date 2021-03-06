package vggames.shared

import vggames.shared.task.Tasks

trait GameEngine {

  val tasks : Tasks

  def description : String

  final lazy val path : String = this.getClass().getSimpleName().replaceAll("Game$", "").toLowerCase()

  def name : String = path.capitalize

  def resourceDescription : Option[ResourceDescription] = None

  def hasTutor = false

}
