package vggames.shared.task;

trait TaskWithDescription[T] extends Task[T] {

  def getDescription : String

  def getGroupName : String

  def getGroupCode : String

}
