package vggames.shared.task

import eu.henkelmann.actuarius.Transformer

object Markdown {

  def apply(source: String, game: String) = new Markdown(Some(game))(source)

  def apply(source: String) = new Markdown(None)(source)

}

class Markdown private (game: Option[String]) extends Transformer {

  override def apply(source: String) =
    super.apply(source).replaceAll("<pre><code>", s"""<pre class="prettyprint"><code ${classFor(game)}>""")

  private def classFor(game: Option[String]) = game.map(g => s"""class="language-${g}"""").getOrElse("")

}
