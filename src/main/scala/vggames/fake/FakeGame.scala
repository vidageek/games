package vggames.fake

import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.Descriptions
import vggames.shared.task.TaskGroup

class FakeGame(descriptions : Descriptions) extends Game {

  val tasks = new Tasks(create)

  def create = new TaskGroup("Como criar um game?", "fake.game", descriptions)

  def getDescription = "Um jogo para aprender a fazer outros Jogos"

  def getName = "Fake"

}