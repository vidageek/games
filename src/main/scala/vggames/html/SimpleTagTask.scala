package vggames.html

abstract class SingleTagTask(tagName : String) {
  def apply() = new HtmlTask("", "simple.%s".format(tagName))
}

object P extends SingleTagTask("p")