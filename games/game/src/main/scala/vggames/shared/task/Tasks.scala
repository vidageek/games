package vggames.shared.task

class Tasks(taskGroups : TaskGroup*) {

  @volatile private var unmodifiableTasks : Option[Seq[IndexedTask]] = None

  private def tasks(descriptions : Descriptions) : Seq[IndexedTask] =
    unmodifiableTasks match {
      case Some(seq) => seq
      case None =>
        unmodifiableTasks = Option(unmodifiableIndexedTasks(descriptions))
        unmodifiableTasks.get
    }

  def at(index : Int, descriptions : Descriptions) = tasks(descriptions)(index)

  lazy val size = taskGroups.foldLeft(0)(_ + _.tasks.size)

  def all(descriptions : Descriptions) : Seq[IndexedTask] = tasks(descriptions)

  private def unmodifiableIndexedTasks(descriptions : Descriptions) : Vector[IndexedTask] = {
    taskGroups.foldLeft((0, Vector[IndexedTask]())) { (acc, group) =>
      group.foldLeft(acc) { (acc, task) =>
        (acc._1 + 1, acc._2 :+ new IndexedTask(group, descriptions, task, acc._1))
      }
    }._2
  }
}