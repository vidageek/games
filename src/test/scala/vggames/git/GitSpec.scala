package vggames.git

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GitSpec extends Specification {

  "git" should {
    "add commit" in {
      EmptyGit() ~ Commit("commit") should_== Git(List(Commit("commit")))
    }

    "add 2 commits" in {
      EmptyGit() ~ Commit("commit") ~ Commit("second") should_== Git(List(Commit("commit"), Commit("second")))
    }
  }

}