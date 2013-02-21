package vggames.html

import org.specs2.mutable.Specification

class SingleTagTaskSpec extends Specification {

  object Tag extends SingleTagTask("tag")

  "single tag task" should {
    "set resource using tag name" in {
      Tag().resource should_== "simple.tag"
    }

    "set challenge using tag name" in {
      Tag().getChallenge should_== "Crie uma tag <code>tag</code>"
    }
  }

}