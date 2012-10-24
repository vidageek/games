package vggames.shared.task

class TaskGroup(name : String, val groupName : String, descriptions : Descriptions, tasks : Task*) {

  def getDescription = descriptions.forGroup(groupName)

  def getName = name

  def foldLeft[T](t : T) = tasks.foldLeft(t) _
}