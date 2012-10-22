package vggames.shared.task.status

import vggames.shared.task.JudgedTask

class Faileds extends JudgedTask {

  private var faileds = List[Failed]()

  def ok : Boolean = faileds.isEmpty

  def reason : String = faileds.mkString("<br />")

  def addOnlyJudgedFailed(judgedTask : JudgedTask) {
    judgedTask match {
      case task : Failed => faileds = faileds :+ task
      case _ =>
    }
  }

  def addAll(faileds : Faileds) : Unit = faileds.foreach(addOnlyJudgedFailed)

  def judgment() : JudgedTask = if (ok) new Ok() else new Failed(this)

  private def foreach = faileds.foreach _

  def mkString = faileds.mkString(_ : String)
}
