package vggames.html

import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup
import vggames.shared.ResourceDescription
import vggames.shared.GameEngine

class HtmlGame extends GameEngine {

  override val tasks = new Tasks()

  def description = "Um jogo muito legal para aprender Html, focado em Html5"

  def name = "Html"

  def path = "html"

  override def resourceDescription = Some(ResourceDescription("text/html", "html", "html"))

}
