package vggames.shared.task

class TaskGroup(name : String, val groupName : String, tasks : Task[_]*) {

  lazy val getName = Markdown(name).replaceAll("</?p>", "").replaceAll("\n", "")

  def foldLeft[T](t : T) = tasks.foldLeft(t) _
}