package vggames.shared

import br.com.caelum.vraptor.{ Get, Post, Resource, Result }
import scala.collection.JavaConverters._
import vggames.shared.log.{ Log, Submission }
import vggames.shared.player.PlayerSession
import vggames.shared.task.JudgedTask
import vggames.shared.task.NotLoggedJudgedTask
import vggames.shared.player.Player
import vggames.shared.vraptor.VraptorExtensions._
import vggames.shared.view.Index
import vggames.shared.vraptor.Params

@Resource
class GameConsole(result : Result, game : Game, log : Log, session : PlayerSession, params : Params) {

  @Get(Array("/play/{gameName}"))
  def index(gameName : String) {
    result.render(new Index)(gameName, game, session.finishedGroups, params.gameEnded)
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

  @Post(Array("/play/{gameName}/task/{index}"))
  def submit(gameName : String, index : Int, challenge : String) {
    val cleanChallenge = if (challenge == null) "" else challenge

    val task = game.task(index)
    val judgedTask = task.judge(cleanChallenge)

    log.log(Submission(gameName, task, cleanChallenge, judgedTask, session.actualPlayer, session.ip))

    result.include("judgedTask", personalize(judgedTask, session.actualPlayer))

    judgedTask.success {

      game.advance(index) { nextIndex =>
        session.saveLast(s"/play/${gameName}/task/${nextIndex}")
        result.redirectTo(this).task(gameName, nextIndex)
      }

      game.atGroupEnd(index) {
        session.finishGroup(gameName + "." + task.getGroupCode)
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

  private def personalize(task : JudgedTask, player : Option[Player]) : JudgedTask = player.map(a => task).getOrElse(new NotLoggedJudgedTask(task))

  private def taskExists(index : Int) = index >= 0 && index < game.getSize
}
