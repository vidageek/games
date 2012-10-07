package vggames.shared.task

class TaskGroup(name : String, groupName : String, descriptions : Descriptions, tasks : Task*) {

  def task(index : Int) = tasks(index)

  def getDescription = descriptions.forGroup(groupName)

  def getName = name

  def foreach = tasks.foreach _
}