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

    "understand commit syntax with -a flag" in {
      Command("""git commit -a -m "mensagem" """) should_== Some(Commit("mensagem", true))
    }

    "understand commit syntax with -a flag even if it comes after the message" in {
      Command("""git commit -m "mensagem" -a""") should_== Some(Commit("mensagem", true))
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

    "understand branch syntax for delete" in {
      Command("""git branch -D asdrubal """) should_== Some(DeleteBranch("asdrubal"))
    }

    "understand branch syntax for move" in {
      Command("""git branch -m work asdrubal""") should_== Some(MoveBranch("work", "asdrubal"))
    }

    "understand init syntax" in {
      Command("""git init asdrubal """) should_== Some(Init("asdrubal"))
    }

    "understand add syntax" in {
      Command("""git add asdrubal""") should_== Some(Add("asdrubal"))
      Command("""git add a.txt""") should_== Some(Add("a.txt"))
      Command("""git add .""") should_== Some(Add("."))
    }
  }

}