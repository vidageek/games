package vggames.shared

import java.util.Collection
import vggames.shared.task.{ TaskWithDescription, Tasks }

trait GameEngine {

  val tasks : Tasks

  def getDescription : String

  def getName : String

  def path : String

  def resourceDescription : Option[ResourceDescription] = None

}
