package vggames.shared.task

case class TaskGroup(name : String, id : String, tasks : Task*) {

  lazy val htmlName = Markdown(name).replaceAll("</?p>", "").replaceAll("\n", "")

  def foldLeft[T](t : T) = tasks.foldLeft(t) _

}