package vggames.shared.view

import br.com.caelum.vraptor.View
import scalatags._

trait TypedView[T] {

  def render(t : T) : HtmlTag = html()

  def renderString(t : T) = render(t).toString

  def contentType = "text/html"

}