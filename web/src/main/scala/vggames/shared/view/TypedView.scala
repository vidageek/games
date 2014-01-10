package vggames.shared.view

import br.com.caelum.vraptor.View
import scalatags.HtmlTag

trait TypedView[T] {

  def render(t : T) : HtmlTag

}