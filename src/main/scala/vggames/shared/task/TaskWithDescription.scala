package vggames.shared.task;

trait TaskWithDescription extends Task {

  def getDescription : String

  def getGroupName : String

  def getGroupCode : String

}
