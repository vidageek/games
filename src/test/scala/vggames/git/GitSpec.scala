package vggames.git

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GitSpec extends Specification {

  "git" should {
    "add commit" in {
      (EmptyGit() ~ Commit("commit")).commits should_== Map("work" -> List(Commit("commit")))
    }

    "add 2 commits" in {
      (EmptyGit() ~ Commit("commit") ~ Commit("second")).commits should_==
        Map("work" -> List(Commit("commit"), Commit("second")))
    }

    "create branch" in {
      (EmptyGit() ~ Branch("asdrubal")).commits should_== Map("asdrubal" -> List(), "work" -> List())
      (EmptyGit() ~ Branch("asdrubal")).branch should_== "work"
    }

    "create branch with commits from parent branch" in {
      (EmptyGit() ~ Commit("c1") ~ Branch("asdrubal")).commits should_==
        Map("asdrubal" -> List(Commit("c1")), "work" -> List(Commit("c1")))
    }

    "should keep branch if already exists" in {
      (EmptyGit() ~ Commit("asdf") ~ Branch("work")).commits should_== Map("work" -> List(Commit("asdf")))

    }

    "checkout to existing branch" in {
      (EmptyGit() ~ Checkout("work")).branch should_== "work"
    }

    "create and checkout to branch if flag -o is present" in {
      (EmptyGit() ~ Checkout("master", true)).branch should_== "master"
    }

    "not checkout if branch does not exists" in {
      (EmptyGit() ~ Checkout("master")).branch should_== "work"
    }

    "generate task sequence representing all repo mutations" in {
      val tasks = (EmptyGit() ~ Commit("c1") ~ Checkout("abc", true) ~ Commit("c2")).tasks
      tasks.size should_== 3
      tasks(0).original.commits should_== Map("work" -> List())
      tasks(0).original.branch should_== "work"
      tasks(0).expected.commits should_== Map("work" -> List(Commit("c1")))
      tasks(0).expected.branch should_== "work"

      tasks(1).original.commits should_== Map("work" -> List(Commit("c1")))
      tasks(1).original.branch should_== "work"
      tasks(1).expected.commits should_== Map("work" -> List(Commit("c1")), "abc" -> List(Commit("c1")))
      tasks(1).expected.branch should_== "abc"

      tasks(2).original.commits should_== Map("work" -> List(Commit("c1")), "abc" -> List(Commit("c1")))
      tasks(2).original.branch should_== "abc"
      tasks(2).expected.commits should_== Map("work" -> List(Commit("c1")), "abc" -> List(Commit("c1"), Commit("c2")))
      tasks(2).expected.branch should_== "abc"
    }

    "not register previous command if ~< was called" in {
      (EmptyGit() ~ Branch("branch") ~< Commit("a")).commits should_== Map("work" -> List(Commit("a")), "branch" -> List())
      (EmptyGit() ~ Branch("branch") ~< Commit("a")).parent should_== EmptyGit()
    }
  }

  "git commits" should {
    "present all created branches (even if empty)" in {
      (EmptyGit() ~ Branch("abc")).findCommits should_== List(CommitList("work", List()), CommitList("abc", List()))
      (EmptyGit() ~ Checkout("abc", true)).findCommits should_== List(CommitList("work", List()), CommitList("abc", List()))
    }

    "be ordered from closer to programmer" in {
      Git(null, null, Map("stash" -> List(), "work" -> List(), "master" -> List(), "origin/master" -> List()), "work").findCommits should_==
        List(CommitList("stash", List()), CommitList("work", List()), CommitList("master", List()), CommitList("origin/master", List()))
    }

    "keep other local branches between work and master" in {
      Git(null, null, Map("abc" -> List(), "work" -> List(), "master" -> List()), "work").findCommits should_==
        List(CommitList("work", List()), CommitList("abc", List()), CommitList("master", List()))
    }

    "keep other remote branches after origin/master" in {
      Git(null, null, Map("origin/master" -> List(), "origin/stable" -> List()), "origin/stable").findCommits should_==
        List(CommitList("origin/master", List()), CommitList("origin/stable", List()))
    }
  }
}
