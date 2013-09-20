package vggames.html

abstract class SingleTagTask(tagName : String) {
  def apply() = new HtmlTask("", s"simple.${tagName}")
}

object P extends SingleTagTask("p")