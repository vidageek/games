package vggames.shared

import br.com.caelum.vraptor.{ Get, Post, Resource, Result }
import br.com.caelum.vraptor.ioc.Component
import vggames.shared.log.{ Log, Submission }
import vggames.shared.player.PlayerSession

@Resource
class GameConsole(result : Result, game : Game, log : Log, session : PlayerSession) {

  @Get(Array("/play/{gameName}"))
  def index(gameName : String) {
    result.include("gameName", gameName)
    result.include("game", game)
  }

  @Get(Array("/play/{gameName}/task/{index}"))
  def task(gameName : String, index : Int) {
    if (taskExists(index)) {
      result.include("gameName", gameName)
      result.include("task", game.task(index))
      result.include("game", game)
    } else {
      result.redirectTo(this).index(gameName)
    }
  }

  @Post(Array("/play/{outterGameName}/task/{outterIndex}"))
  def submit(outterGameName : String, outterIndex : Int, challenge : String) {
    val gameName = outterGameName
    val index = outterIndex
    val cleanChallenge = if (challenge == null) "" else challenge

    val task = game.task(index)
    val judgedTask = task.judge(cleanChallenge)

    log.log(Submission(gameName, task, cleanChallenge, judgedTask))

    result.include("judgedTask", judgedTask)

    judgedTask.success {

      game.advance(index) { nextIndex =>
        session.saveLast("/play/%s/task/%d".format(gameName, nextIndex))
        result.redirectTo(this).task(gameName, nextIndex)
      }

      game.atEnd(index) {
        session.endGame
        result.include("gameEnded", "end")
        result.redirectTo(this).index(gameName)
      }
    }

    judgedTask.failure {
      result.include("challenge", cleanChallenge)
      result.redirectTo(this).task(gameName, index)
    }
  }

  private def taskExists(index : Int) = index >= 0 && index < game.getSize
}
