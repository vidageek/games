package vggames.shared.task

class Tasks(taskGroups : TaskGroup*) {

  def withDescriptions(descriptions : Descriptions) : Vector[IndexedTask] = {
    taskGroups.foldLeft((0, Vector[IndexedTask]())) { (acc, group) =>
      group.foldLeft(acc) { (acc, task) =>
        (acc._1 + 1, acc._2 :+ new IndexedTask(group, descriptions, task, acc._1))
      }
    }._2
  }
}