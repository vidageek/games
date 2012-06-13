package vggames

import vggames.task.JudgedTask
import br.com.caelum.vraptor.{Get, Post, Resource, Result}

@Resource
class GameConsole(result: Result, game: Game) {

  @Get(Array("/play/{gameName}"))
  def index(gameName: String) {
    result.include("gameName", gameName)
    result.include("game", game)
  }

  @Get(Array("/play/{gameName}/task/{index}"))
  def task(gameName: String, index: Int) {
    result.include("gameName", gameName)
    result.include("task", game.task(index))
    result.include("game", game)
  }

  @Post(Array("/play/{gameName}/task/{index}"))
  def submit(gameName: String, index: Int, challenge: String) {
    val judgedTask = game.task(index).judge(challenge)
    result.include("judgedTask", judgedTask)
    if (judgedTask.getOk) {
      if (game.hasNextTask(index)) {
        result.redirectTo(this).task(gameName, game.nextTask(index))
      } else {
        result.redirectTo(this).index(gameName)
      }
    } else {
      result.include("challenge", challenge)
      result.redirectTo(this).task(gameName, index)
    }
  }
}
