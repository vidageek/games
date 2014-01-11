package vggames.shared

import vggames.shared.task.TaskWithDescription

trait GameView {

  def render(game : Game, task : TaskWithDescription[_]) : String

}