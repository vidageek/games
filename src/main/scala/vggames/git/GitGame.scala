package vggames.git

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class GitGame(descriptions : Descriptions) extends Game {

  val tasks = new Tasks(init, add, commit, branch, checkout, merge)

  def init = {
    val tasks = (EmptyGit() ~ Init("repositorio")).tasks ++ (EmptyGit() ~ Init("repo2")).tasks
    new TaskGroup("Criar um reposit&oacute;rio", "git.init", descriptions, tasks : _*)
  }

  def add = {
    val tasks = (EmptyGit() ~< UntrackedFile("arquivo.txt") ~ Add("arquivo.txt")).tasks ++
      (EmptyGit() ~< UntrackedFile("pasta/arquivo.txt") ~< UntrackedFile("pasta/arquivo2.txt") ~ Add("pasta")).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivo") ~ Add("arquivo")).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivos/arq") ~< ModifiedFile("arquivos/arq2") ~ Add("arquivos")).tasks ++
      (EmptyGit() ~< UntrackedFile("arquivo1.txt") ~< UntrackedFile("arquivo2.txt") ~ Add("arquivo1.txt")).tasks ++
      (EmptyGit() ~< ModifiedFile("arq1") ~< ModifiedFile("arq2") ~ Add("arq2")).tasks ++
      (EmptyGit() ~< UntrackedFile("arquivo1.txt") ~< ModifiedFile("arquivo2.txt") ~ Add(".")).tasks ++
      (EmptyGit() ~< UntrackedFile("arquivo1.txt") ~< ModifiedFile("arquivo2.txt") ~ Add("arquivo1.txt") ~ Add(".")).tasks

    new TaskGroup("Adicionar arquivos", "git.add", descriptions, tasks : _*)
  }

  def commit = {
    val tasks = (EmptyGit() ~< CandidateFile("arquivo.txt") ~ Commit("meu primeiro commit")).tasks ++
      (EmptyGit() ~< UntrackedFile("arquivo.txt") ~ Add("arquivo.txt") ~ Commit("commit de um arquivo untracked")).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivo.txt") ~< UntrackedFile("arquivo2.txt") ~ Add(".") ~ Commit("commit de um arquivo untracked e um modified")).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivo.txt") ~ Commit("commit de um arquivo modified sem usar git add", true)).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivo2.txt") ~< UntrackedFile("arquivo.txt") ~ Add("arquivo.txt") ~ Commit("commit apenas do arquivo.txt") ~ Commit("commit do arquivo2.txt sem usar add", true)).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivo2.txt") ~< UntrackedFile("arquivo.txt") ~< ModifiedFile("pasta/arquivo3.txt") ~< UntrackedFile("pasta/arquivo4.txt") ~<
        ModifiedFile("outraPasta/arquivo5.txt") ~< ModifiedFile("outraPasta/arquivo6.txt") ~ Add("arquivo2.txt") ~ Commit("commit apenas do arquivo2.txt") ~
        Add("pasta") ~ Commit("commit dos arquivos da pasta 'pasta'") ~ Add("arquivo.txt") ~ Commit("commit do arquivo.txt") ~ Commit("commit dos arquivos que faltam", true)).tasks

    new TaskGroup("Fazer Commits", "git.commit", descriptions, tasks : _*)
  }

  def branch = {
    val tasks = (EmptyGit() ~ Branch("work") ~ Branch("feature") ~ DeleteBranch("work") ~ Branch("master")
      ~ MoveBranch("master", "outroBranch") ~ DeleteBranch("outroBranch") ~ Branch("master")).tasks
    new TaskGroup("Criar Branches", "git.branch", descriptions, tasks : _*)
  }

  def checkout = {
    val tasks = (EmptyGit() ~ Branch("work") ~ Checkout("work")).tasks ++
      (EmptyGit() ~ Checkout("work", true) ~ Checkout("outraFeature", true)).tasks ++
      (EmptyGit() ~ Commit("commit no master") ~ Branch("work") ~ Commit("outro commit no master") ~ Checkout("work")
        ~ Commit("commit no work") ~ Checkout("outro", true) ~ Commit("commit no branch outro") ~ Checkout("work") ~
        Commit("mais um commit no work")).tasks
    new TaskGroup("Mudar de branch ativo", "git.checkout", descriptions, tasks : _*)
  }

  def merge = {
    val tasks = (EmptyGit() ~ Checkout("work", true) ~ Branch("outro") ~ Commit("primeiro commit") ~ Checkout("outro")
      ~ Merge("work") ~ Commit("commit no branch outro") ~ Checkout("work") ~ Commit("commit no work") ~ Merge("outro")
      ~ Commit("commit depois do commit de merge") ~ Checkout("outro")).tasks
    new TaskGroup("Merge de branches", "git.merge", descriptions, tasks : _*)
  }

  def getDescription = "Um jogo muito legal para aprender Git"

  def getName = "Git"

}