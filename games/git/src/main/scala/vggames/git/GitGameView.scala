package vggames.git

import vggames.shared.GameView
import vggames.shared.Game
import scalatags.Text.all._
import vggames.shared.task.JudgedTask
import vggames.shared.task.Exercise
import scalatags.text.Frag

class GitGameView extends GameView {

  private def files(git: Git, state: String, content: String) = {
    div(cls := "span4")(
      git.files.get(state).filterNot(_.isEmpty).map { files =>
        List(
          raw(content),
          ul(cls := s"files-${state}")(
            files.map { file =>
              li(file.toString)
            }))
      }.getOrElse[List[Frag]](List(raw("\n\n"))))
  }

  def render(game: Game, task: Exercise, judgedTask: Option[JudgedTask], lastAttempt: String) = {
    val extraData = task.extraData.get.asInstanceOf[Git]

    div(cls := "git")(
      div(cls := "row")(

        extraData.findCommits.map { commits =>
          div(cls := s"span3 branch branch-${commits.branch}")(
            span(cls := "branch")(commits.branch),
            if (extraData.branch == commits.branch) span(cls := "active")(" (ativo)") else "",
            ul(
              commits.commits.map { commit =>
                li(commit.toString)
              }))

        }, "\n\n"),
      div(cls := "row")(
        files(extraData, "candidate", "Changes to be commited"),
        files(extraData, "modified", "Modified"),
        files(extraData, "untracked", "Untracked"))).toString + "\n" +
      div(cls := "row")(
        div(cls := "span6")(
          judgement(judgedTask),
          singleLineChallengeForm(game, task, lastAttempt, "Next! (ctrl + enter)", "span5"),
          progressBar(task, game)),
        taskDescription(task, "span6")).toString
  }
}