package vggames.shared

import br.com.caelum.vraptor.{ Result, Resource, Post, Get }
import vggames.shared.log.Log
import vggames.shared.log.Submission

@Resource
class GameConsole(result : Result, game : Game, log : Log) {

  @Get(Array("/play/{gameName}"))
  def index(gameName : String) {
    result.include("gameName", gameName)
    result.include("game", game)
  }

  @Get(Array("/play/{gameName}/task/{index}"))
  def task(gameName : String, index : Int) {
    result.include("gameName", gameName)
    result.include("task", game.task(index))
    result.include("game", game)
  }

  @Post(Array("/play/{gameName}/task/{index}"))
  def submit(gameName : String, index : Int, challenge : String) {
    val cleanChallenge = if (challenge == null) "" else challenge

    val task = game.task(index)
    val judgedTask = task.judge(cleanChallenge)

    log.log(Submission(gameName, task, cleanChallenge, judgedTask))

    result.include("judgedTask", judgedTask)
    if (judgedTask.getOk) {
      if (game.hasNextTask(index)) {
        result.redirectTo(this).task(gameName, game.nextTask(index))
      } else {
        result.include("gameEnded", "end")
        result.redirectTo(this).index(gameName)
      }
    } else {
      result.include("challenge", cleanChallenge)
      result.redirectTo(this).task(gameName, index)
    }
  }
}
