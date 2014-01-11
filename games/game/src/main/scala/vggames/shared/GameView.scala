package vggames.shared

import vggames.shared.task.TaskWithDescription
import vggames.shared.task.JudgedTask
import scalatags._
import vggames.shared.task.Task

trait GameView {

  def render(game : Game, task : TaskWithDescription, judgedTask : Option[JudgedTask], lastAttempt : String) : String

  def progressBar(task : TaskWithDescription, game : Game) =

    div("progress".cls)(
      div("bar".cls, style := s"width: ${(task.getIndex.toDouble / game.getSize) * 100}%;")(""))

}