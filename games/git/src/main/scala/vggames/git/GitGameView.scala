package vggames.git

import vggames.shared.GameView
import vggames.shared.Game
import scalatags._
import vggames.shared.task.JudgedTask
import vggames.shared.task.Exercise

class GitGameView extends GameView {

  private def files(git : Git, state : String, content : String) = {
    div("span4".cls)(
      git.files.get(state).filterNot(_.isEmpty).map { files =>
        List(
          raw(content),
          ul(s"files-${state}".cls)(
            files.map { file =>
              li(file.toString)
            }))
      }.getOrElse[List[Node]](List(raw("\n\n"))))
  }

  def render(game : Game, task : Exercise, judgedTask : Option[JudgedTask], lastAttempt : String) = {
    val extraData = task.extraData.get.asInstanceOf[Git]

    div("git".cls)(
      div("row".cls)(

        extraData.findCommits.map { commits =>
          div(s"span3 branch branch-${commits.branch}".cls)(
            span("branch".cls)(commits.branch),
            if (extraData.branch == commits.branch) span("active".cls)(" (ativo)") else "",
            ul(
              commits.commits.map { commit =>
                li(commit.toString)
              }))

        }, "\n\n"),
      div("row".cls)(
        files(extraData, "candidate", "Changes to be commited"),
        files(extraData, "modified", "Modified"),
        files(extraData, "untracked", "Untracked"))).toString + "\n" +
      div("row".cls)(
        div("span6".cls)(
          judgement(judgedTask),
          singleLineChallengeForm(game, task, lastAttempt, "Next! (ctrl + enter)", "span5"),
          progressBar(task, game)),
        taskDescription(task, "span6")).toString
  }
}