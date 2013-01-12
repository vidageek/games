package vggames.fake

import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.Descriptions

class FakeGame(descriptions : Descriptions) extends Game {

  val tasks = new Tasks()

  def getDescription = "Um jogo para aprender a fazer outros Jogos"

  def getName = "Fake"

}