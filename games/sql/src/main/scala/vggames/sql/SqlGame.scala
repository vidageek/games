package vggames.sql

import vggames.shared.GameEngine
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class SqlGame extends GameEngine {

  val tasks = new Tasks(
    test)

  private def test = new TaskGroup("Blablabla", "test", new Create("tabela"))

  def description = ""

  def path = "sql"

}