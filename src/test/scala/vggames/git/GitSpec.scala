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

  "commits" should {
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

  "diff" should {
    "return empty list if no diferences are found" in {
      EmptyGit().diff(EmptyGit()) should_== List()
    }

    "return empty list if parent is different" in {
      EmptyGit().diff(Git(EmptyGit(), null, Map("work" -> List()), "work")) should_== List()
    }

    "return empty list if last command is different" in {
      EmptyGit().diff(Git(null, Branch("a"), Map("work" -> List()), "work")) should_== List()
    }

    "return actual branch differences" in {
      Git(null, null, Map(), "work").diff(Git(null, null, Map(), "work2")) should_==
        List("Deveria mudar para o branch <code>work2</code>. Est&aacute; em <code>work</code>")
    }

    "return branch list differences" in {
      Git(null, null, Map("work" -> List(), "asdrubal" -> List()), "work").
        diff(Git(null, null, Map("work2" -> List(), "asdrubal2" -> List()), "work")) should_==
        List("Deveria criar o branch <code>work2</code>.", "Deveria criar o branch <code>asdrubal2</code>.",
          "N&atilde;o deveria criar o branch <code>work</code>.", "N&atilde;o deveria criar o branch <code>asdrubal</code>.")
    }

    "return differences in commit lists" in {
      Git(null, null, Map("work" -> List(Commit("1")), "asdrubal" -> List(Commit("3"))), "work").
        diff(Git(null, null, Map("work" -> List(Commit("2")), "asdrubal" -> List(Commit("4"))), "work")) should_==
        List("Commit <code>0</code> do branch <code>work</code> deveria ser <code>2</code>.",
          "Commit <code>0</code> do branch <code>asdrubal</code> deveria ser <code>4</code>.")
    }

  }
}
