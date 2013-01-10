package vggames.git

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GitSpec extends Specification {

  "empty git" should {
    "have no active branch" in {
      EmptyGit().branch must_== ""
    }

    "have no branch" in {
      EmptyGit().commits must_== Map()
    }

    "have no repository" in {
      EmptyGit().repo must_== ""
    }
  }

  "work git" should {
    "have work as active branch" in {
      WorkGit().branch must_== "work"
    }

    "have branch work and master" in {
      WorkGit().commits must_== Map("work" -> List(), "master" -> List())
    }

    "have repository repositorio" in {
      WorkGit().repo must_== "repositorio"
    }
  }

  "git commit" should {

    "create branch master if there is no active branch" in {
      (EmptyGit() ~ Commit("a")).branch should_== "master"
      (EmptyGit() ~ Commit("a")).commits should_== Map("master" -> List(Commit("a")))
    }

    "add commit" in {
      (EmptyGit() ~ Commit("commit")).commits should_== Map("master" -> List(Commit("commit")))
    }

    "add 2 commits" in {
      (EmptyGit() ~ Commit("commit") ~ Commit("second")).commits should_==
        Map("master" -> List(Commit("commit"), Commit("second")))
    }

    "remove all commit candidate files" in {
      (EmptyGit() ~ CandidateFile("a") ~ Commit("a")).files should_== Map("untracked" -> List(), "modified" -> List(), "candidate" -> List())
    }

    "remove all commit candidate and modified files if -a is present" in {
      (EmptyGit() ~ ModifiedFile("b") ~ CandidateFile("a") ~ Commit("a", true)).files should_== Map("untracked" -> List(), "modified" -> List(), "candidate" -> List())
    }

    "not remove all untracked and modified files" in {
      (EmptyGit() ~ UntrackedFile("a") ~ ModifiedFile("b") ~ Commit("a")).files should_==
        Map("untracked" -> List(GitFile("a")), "modified" -> List(GitFile("b")), "candidate" -> List())
    }

    "not remove untracked files when -a is present" in {
      (EmptyGit() ~ UntrackedFile("a") ~ Commit("a", true)).files should_==
        Map("untracked" -> List(GitFile("a")), "modified" -> List(), "candidate" -> List())
    }
  }

  "git branch" should {
    "create branch" in {
      (EmptyGit() ~ Branch("asdrubal")).commits should_== Map("asdrubal" -> List())
      (EmptyGit() ~ Branch("asdrubal")).branch should_== ""
    }

    "delete branch" in {
      (EmptyGit() ~ Branch("asdrubal") ~ DeleteBranch("asdrubal")).commits should_== Map()
    }

    "delete branch but keep others" in {
      (EmptyGit() ~ Branch("work") ~ Branch("asdrubal") ~ DeleteBranch("asdrubal")).commits should_== Map("work" -> List())
    }

    "move branch" in {
      (EmptyGit() ~ Branch("work") ~ MoveBranch("work", "asdrubal")).commits should_== Map("asdrubal" -> List())
    }

    "move branch but keep others" in {
      (EmptyGit() ~ Branch("other") ~ Branch("work") ~ MoveBranch("work", "asdrubal")).commits should_== Map("asdrubal" -> List(), "other" -> List())
    }

    "create branch with commits from parent branch" in {
      (EmptyGit() ~ Commit("c1") ~ Branch("asdrubal")).commits should_==
        Map("asdrubal" -> List(Commit("c1")), "master" -> List(Commit("c1")))
    }

    "should keep branch if already exists" in {
      (EmptyGit() ~ Commit("asdf") ~ Branch("master")).commits should_== Map("master" -> List(Commit("asdf")))

    }
  }

  "git checkout" should {

    "checkout to existing branch" in {
      (EmptyGit() ~ Branch("work") ~ Checkout("work")).branch should_== "work"
    }

    "create and checkout to branch if flag -o is present" in {
      (EmptyGit() ~ Checkout("asdrubal", true)).branch should_== "asdrubal"
    }

    "not checkout if branch does not exists" in {
      (EmptyGit() ~ Checkout("work")).branch should_== ""
    }
  }

  "AddFile" should {

    "add untracked file" in {
      (EmptyGit() ~ UntrackedFile("a")).files should_== Map("untracked" -> List(GitFile("a")), "modified" -> List(), "candidate" -> List())
    }

    "keep untracked files sorted" in {
      (EmptyGit() ~ UntrackedFile("z") ~ UntrackedFile("a")).files should_== Map("untracked" -> List(GitFile("a"), GitFile("z")), "modified" -> List(), "candidate" -> List())
    }

    "add modified file" in {
      (EmptyGit() ~ ModifiedFile("a")).files should_== Map("modified" -> List(GitFile("a")), "untracked" -> List(), "candidate" -> List())
    }

    "keep modified files sorted" in {
      (EmptyGit() ~ ModifiedFile("z") ~ ModifiedFile("a")).files should_== Map("modified" -> List(GitFile("a"), GitFile("z")), "untracked" -> List(), "candidate" -> List())
    }

    "add commit candidate file" in {
      (EmptyGit() ~ CandidateFile("a")).files should_== Map("candidate" -> List(GitFile("a")), "untracked" -> List(), "modified" -> List())
    }

    "keep commit candidate files sorted" in {
      (EmptyGit() ~ CandidateFile("z") ~ CandidateFile("a")).files should_== Map("candidate" -> List(GitFile("a"), GitFile("z")), "untracked" -> List(), "modified" -> List())
    }
  }

  "git add" should {

    "not add file if it doesn't match path" in {
      (EmptyGit() ~ UntrackedFile("a") ~< Add("b")).files should_== Map("untracked" -> List(GitFile("a")), "modified" -> List(), "candidate" -> List())
      (EmptyGit() ~ ModifiedFile("a") ~< Add("b")).files should_== Map("modified" -> List(GitFile("a")), "untracked" -> List(), "candidate" -> List())
    }

    "move file from untracked to commit candidate" in {
      (EmptyGit() ~ UntrackedFile("a") ~< Add("a")).files should_== Map("candidate" -> List(GitFile("a")), "untracked" -> List(), "modified" -> List())
    }

    "move file from modified to commit candidate" in {
      (EmptyGit() ~ ModifiedFile("a") ~ Add("a")).files should_== Map("candidate" -> List(GitFile("a")), "untracked" -> List(), "modified" -> List())
    }

    "move files that matched path to commit candidate" in {
      (EmptyGit() ~ ModifiedFile("asdf") ~ UntrackedFile("asdrubal") ~ Add("as")).files should_== Map("candidate" -> List(GitFile("asdf"), GitFile("asdrubal")), "untracked" -> List(), "modified" -> List())
    }

    "move all files to commit candidate if path is ." in {
      (EmptyGit() ~ ModifiedFile("asdf") ~ UntrackedFile("asdrubal") ~ Add(".")).files should_== Map("candidate" -> List(GitFile("asdf"), GitFile("asdrubal")), "untracked" -> List(), "modified" -> List())
    }

    "keep candidate files" in {
      (EmptyGit() ~ CandidateFile("a") ~ ModifiedFile("b") ~ UntrackedFile("c") ~ Add(".")).files should_== Map("candidate" -> List(GitFile("a"), GitFile("b"), GitFile("c")), "untracked" -> List(), "modified" -> List())
    }

    "keep all files sorted" in {
      (EmptyGit() ~ CandidateFile("a") ~ CandidateFile("b") ~ CandidateFile("c") ~ ModifiedFile("a1") ~ ModifiedFile("b1") ~ ModifiedFile("c1") ~
        UntrackedFile("a2") ~ UntrackedFile("b2") ~ UntrackedFile("c2") ~ Add("b")).files should_==
        Map("candidate" -> List(GitFile("a"), GitFile("b"), GitFile("b1"), GitFile("b2"), GitFile("c")), "untracked" -> List(GitFile("a2"), GitFile("c2")),
          "modified" -> List(GitFile("a1"), GitFile("c1")))
    }
  }

  "git merge" should {
    "merge commits from other branch into actual branch" in {
      (EmptyGit() ~ Branch("work") ~ Commit("a") ~ Checkout("work") ~ Merge("master")).commits should_==
        Map("master" -> List(Commit("a")), "work" -> List(Commit("a")))
    }

    "merge commits (above the base) from other branch into actual branch" in {
      (EmptyGit() ~ Commit("a") ~ Branch("work") ~ Commit("b") ~ Checkout("work") ~ Merge("master")).commits should_==
        Map("master" -> List(Commit("a"), Commit("b")), "work" -> List(Commit("a"), Commit("b")))
    }

    "add merge commit if actual branch has more commits than base" in {
      (EmptyGit() ~ Commit("a") ~ Branch("work") ~ Commit("b") ~ Checkout("work") ~ Commit("c") ~ Merge("master")).commits should_==
        Map("master" -> List(Commit("a"), Commit("b")), "work" -> List(Commit("a"), Commit("c"), Commit("b"), Commit("Merge branch master")))
    }
  }
  
  "git rebase" should {
    "rebase commits from other branch into actual branch" in {
      (EmptyGit() ~ Branch("work") ~ Commit("a") ~ Checkout("work") ~ Rebase("master")).commits should_==
          Map("master" -> List(Commit("a")), "work" -> List(Commit("a")))
    }
    
    "rebase commits (above the base) from other branch into actual branch" in {
      (EmptyGit() ~ Commit("a") ~ Branch("work") ~ Commit("b") ~ Checkout("work") ~ Rebase("master")).commits should_==
          Map("master" -> List(Commit("a"), Commit("b")), "work" -> List(Commit("a"), Commit("b")))
    }
    
    "apply commit over actual branch if actual branch has more commits than base" in {
      (EmptyGit() ~ Commit("a") ~ Branch("work") ~ Commit("b") ~ Checkout("work") ~ Commit("c") ~ Rebase("master")).commits should_==
          Map("master" -> List(Commit("a"), Commit("b")), "work" -> List(Commit("a"), Commit("b"), Commit("c")))
    }
  }

  "git" should {

    "generate task sequence representing all repo mutations" in {
      val tasks = (EmptyGit() ~ Commit("c1") ~ Checkout("abc", true) ~ Commit("c2")).tasks
      tasks.size should_== 3
      tasks(0).original.commits should_== Map()
      tasks(0).original.branch should_== ""
      tasks(0).expected.commits should_== Map("master" -> List(Commit("c1")))
      tasks(0).expected.branch should_== "master"

      tasks(1).original.commits should_== Map("master" -> List(Commit("c1")))
      tasks(1).original.branch should_== "master"
      tasks(1).expected.commits should_== Map("master" -> List(Commit("c1")), "abc" -> List(Commit("c1")))
      tasks(1).expected.branch should_== "abc"

      tasks(2).original.commits should_== Map("master" -> List(Commit("c1")), "abc" -> List(Commit("c1")))
      tasks(2).original.branch should_== "abc"
      tasks(2).expected.commits should_== Map("master" -> List(Commit("c1")), "abc" -> List(Commit("c1"), Commit("c2")))
      tasks(2).expected.branch should_== "abc"
    }

    "generate tasks keeping state (but skiping a parent) if ~< was called" in {
      val tasks = (EmptyGit() ~< ModifiedFile("abc") ~ Add("abc")).tasks
      tasks.size should_== 1
      tasks(0).original.files should_== Map("untracked" -> List(), "modified" -> List(GitFile("abc")), "candidate" -> List())
      tasks(0).expected.files should_== Map("untracked" -> List(), "modified" -> List(), "candidate" -> List(GitFile("abc")))

    }
  }

  "commits" should {
    "present all created branches (even if empty)" in {
      (EmptyGit() ~ Branch("abc")).findCommits should_== List(CommitList("abc", List()))
      (EmptyGit() ~ Checkout("abc", true)).findCommits should_== List(CommitList("abc", List()))
    }

    "be ordered from closer to programmer" in {
      Git("repo", null, null, Map("stash" -> List(), "work" -> List(), "master" -> List(), "origin/master" -> List()), "work", Map()).findCommits should_==
        List(CommitList("stash", List()), CommitList("work", List()), CommitList("master", List()), CommitList("origin/master", List()))
    }

    "keep other local branches between work and master" in {
      Git("repo", null, null, Map("abc" -> List(), "work" -> List(), "master" -> List()), "work", Map()).findCommits should_==
        List(CommitList("work", List()), CommitList("abc", List()), CommitList("master", List()))
    }

    "keep other remote branches after origin/master" in {
      Git("repo", null, null, Map("origin/master" -> List(), "origin/stable" -> List()), "origin/stable", Map()).findCommits should_==
        List(CommitList("origin/master", List()), CommitList("origin/stable", List()))
    }
  }

  "diff" should {
    "return empty list if no diferences are found" in {
      EmptyGit().diff(EmptyGit()) should_== List()
    }

    "return empty list if parent is different" in {
      EmptyGit().diff(Git("", EmptyGit(), null, Map(), "", Map())) should_== List()
    }

    "return empty list if last command is different" in {
      EmptyGit().diff(Git("", null, Branch("a"), Map(), "", Map())) should_== List()
    }

    "return actual branch differences" in {
      Git("repo", null, null, Map(), "work", Map()).diff(Git("repo", null, null, Map(), "work2", Map())) should_==
        List("Deveria mudar para o branch <code>work2</code>. Est&aacute; em <code>work</code>")
    }

    "return repo name differences" in {
      Git("repo", null, null, Map(), "work", Map()).diff(Git("repositorio", null, null, Map(), "work", Map())) should_==
        List("Deveria ter criado o reposit&oacute;rio <code>repositorio</code>. Foi criado o <code>repo</code>")
    }

    "return branch list differences" in {
      Git("repo", null, null, Map("work" -> List(), "asdrubal" -> List()), "work", Map()).
        diff(Git("repo", null, null, Map("work2" -> List(), "asdrubal2" -> List()), "work", Map())) should_==
        List("Deveria criar o branch <code>work2</code>.", "Deveria criar o branch <code>asdrubal2</code>.",
          "N&atilde;o deveria criar o branch <code>work</code>.", "N&atilde;o deveria criar o branch <code>asdrubal</code>.")
    }

    "return differences in commit lists" in {
      Git("repo", null, null, Map("work" -> List(Commit("1")), "asdrubal" -> List(Commit("3"))), "work", Map()).
        diff(Git("repo", null, null, Map("work" -> List(Commit("2")), "asdrubal" -> List(Commit("4"))), "work", Map())) should_==
        List("Commit <code>0</code> do branch <code>work</code> deveria ser <code>2</code>, mas foi <code>1</code>",
          "Commit <code>0</code> do branch <code>asdrubal</code> deveria ser <code>4</code>, mas foi <code>3</code>")
    }

    "return differences in file lists" in {
      Git("repo", null, null, Map(), "work", Map("untracked" -> List(GitFile("a")), "modified" -> List(GitFile("b")), "candidate" -> List())).
        diff(Git("repo", null, null, Map(), "work", Map("untracked" -> List(), "modified" -> List(), "candidate" -> List(GitFile("a"), GitFile("b"), GitFile("z"))))) should_==
        List("Arquivo <code>a</code> deveria estar marcado como candidate.",
          "Arquivo <code>b</code> deveria estar marcado como candidate.",
          "Arquivo <code>z</code> deveria exister como candidate.")
    }

  }
}
