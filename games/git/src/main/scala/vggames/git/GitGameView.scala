package vggames.git

import vggames.shared.GameView
import vggames.shared.Game
import vggames.shared.task.TaskWithDescription
import scalatags._
import vggames.shared.task.JudgedTask

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

  def render(game : Game, task : TaskWithDescription, judgedTask : Option[JudgedTask], lastAttempt : String) = {
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
          judgedTask.map { task =>
            div(id := "challenge-result", s"reason alert ${if (task.ok) "alert-success" else "alert-error"}".cls)(
              raw(task.reason))
          }.getOrElse(raw("")),

          form("challenge".cls, "method".attr := "POST", action := s"/play/${game.path}/task/${task.getIndex}")(
            label("for".attr := "challenge")(strong(raw(task.getChallenge))),
            input("focus span5".cls, name := "challenge", id := "challenge", autocomplete := "off", value := lastAttempt),
            input(id := "challenge-submit", "btn btn-primary".cls, "type".attr := "submit", value := "Next! (ctrl + enter)")),
          progressBar(task, game)),
        div("span6".cls)(
          h2(task.getGroupName),
          raw(task.getDescription))).toString
  }
}