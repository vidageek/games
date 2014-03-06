package vggames.shared.task

class Tasks(taskGroups : TaskGroup*) {

  def withDescriptions(descriptions : Descriptions) : Vector[Exercise] = {
    taskGroups.foldLeft((0, Vector[Exercise]())) { (acc, group) =>
      group.foldLeft(acc) { (acc, task) =>
        (acc._1 + 1, acc._2 :+ new Exercise(group, descriptions, task, acc._1))
      }
    }._2
  }
}