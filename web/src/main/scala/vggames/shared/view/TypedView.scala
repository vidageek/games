package vggames.shared.view

import br.com.caelum.vraptor.View
import scalatags.Text.all._
import scalatags.Text.TypedTag

trait TypedView[T] {

  def render(t: T): TypedTag[String] = html()

  def renderString(t: T) = render(t).toString

  def contentType = "text/html"

}