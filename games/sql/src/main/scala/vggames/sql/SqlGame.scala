package vggames.sql

import vggames.shared.GameEngine
import vggames.shared.task.Tasks

class SqlGame extends GameEngine {

  val tasks = new Tasks()

  def description = ""

  def name = "Sql"

  def path = "sql"

}