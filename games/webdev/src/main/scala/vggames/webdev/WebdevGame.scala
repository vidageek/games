package vggames.webdev

import vggames.shared.task.{ Tasks, TaskGroup }
import vggames.shared.GameEngine
import vggames.shared.task.TaskGroup
import vggames.shared.task.Task
import vggames.shared.task.status.Ok

class WebdevGame extends GameEngine {

  val tasks = new Tasks(
    new GithubProject("Crie um robô", "robo"))

  def description = "webdev game"

}

class TestTask extends Task {

  def judge(challenge : String) = Ok()

  def challenge = "challenge"

  def resource = ""

}
