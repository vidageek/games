package vggames.shared

import java.util.Collection
import vggames.shared.task.{ TaskWithDescription, Tasks }

trait GameEngine {

  val tasks : Tasks

  def description : String

  def name : String

  def path : String

  def resourceDescription : Option[ResourceDescription] = None

}
