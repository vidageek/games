package vggames.git

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CommandSpec extends Specification {

  "Command parser" should {

    "return None if command is invalid" in {
      Command("git asdrubal") should_== None
    }

    "understand commit syntax" in {
      Command("""git commit -m "mensagem" """) should_== Some(Commit("mensagem"))
    }

    "understand checkout syntax" in {
      Command("""git checkout master """) should_== Some(Checkout("master"))
    }

    "understand checkout -b syntax" in {
      Command("""git checkout -b master """) should_== Some(Checkout("master", true))
    }

    "understand branch syntax" in {
      Command("""git branch asdrubal """) should_== Some(Branch("asdrubal"))
    }
  }

}