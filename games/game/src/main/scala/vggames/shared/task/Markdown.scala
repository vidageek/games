package vggames.shared.task

import eu.henkelmann.actuarius.Transformer

object Markdown {

  def apply(source : String) = new Markdown()(source)

}

class Markdown private extends Transformer {

  override def apply(source : String) =
    super.apply(source).replaceAll("<pre><code>", """<pre><code class="prettyprint">""")

}
