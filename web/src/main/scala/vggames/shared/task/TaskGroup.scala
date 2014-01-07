package vggames.shared.task

class TaskGroup(name : String, val groupName : String, descriptions : Descriptions, tasks : Task[_]*) {

  def getDescription = descriptions.forGroup(groupName)

  lazy val getName = Markdown(name).replaceAll("</?p>", "").replaceAll("\n", "")

  def foldLeft[T](t : T) = tasks.foldLeft(t) _
}