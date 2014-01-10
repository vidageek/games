package vggames.git

import vggames.shared.task.{ Descriptions, Tasks, TaskGroup }
import vggames.shared.GameEngine
import br.com.caelum.vraptor.ioc.Component
import vggames.git._
import br.com.caelum.vraptor.ioc.ApplicationScoped
import com.google.inject.Singleton
import com.google.inject.Inject

class GitGame extends GameEngine {

  val descriptions : Descriptions = new Descriptions("git")
  val tasks = new Tasks(init, add, commit, branch, checkout, merge, rebase, push, pull, workflow)

  def init = {
    val tasks = (EmptyGit() ~ Init("repositorio")).tasks ++ (EmptyGit() ~ Init("repo2")).tasks
    new TaskGroup("Criar um repositório", "git.init", descriptions, tasks : _*)
  }

  def add = {
    val tasks = (EmptyGit() ~< UntrackedFile("arquivo.txt") ~ Add("arquivo.txt")).tasks ++
      (EmptyGit() ~< UntrackedFile("pasta/arquivo.txt") ~< UntrackedFile("pasta/arquivo2.txt") ~ Add("pasta", true)).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivo") ~ Add("arquivo")).tasks ++
      (EmptyGit() ~< ModifiedFile("arquivos/arq") ~< ModifiedFile("arquivos/arq2") ~ Add("arquivos", true)).tasks ++
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

  def rebase = {
    val tasks = (EmptyGit() ~ Checkout("work", true) ~ Branch("outro") ~ Commit("primeiro commit") ~ Checkout("outro")
      ~ Rebase("work") ~ Commit("commit no branch outro") ~ Checkout("work") ~ Commit("commit no work") ~ Rebase("outro")
      ~ Commit("mais um commit") ~ Commit("mais outro commit") ~ Checkout("outro") ~ Rebase("work") ~ Checkout("work")).tasks
    new TaskGroup("Rebase de branches", "git.rebase", descriptions, tasks : _*)
  }

  def push = {
    val tasks = (MasterGit() ~ Commit("commit no master") ~ Push("origin", "master") ~ Commit("um commit")
      ~ Commit("dois commits") ~ Push("origin", "master")).tasks ++
      (WorkGit() ~< Branch("origin/master") ~ Commit("commit no work") ~ Checkout("master") ~ Merge("work")
        ~ Push("origin", "master") ~ Checkout("work")).tasks
    new TaskGroup("Enviar commits para branches remotos (Push)", "git.push", descriptions, tasks : _*)
  }

  def pull = {
    val tasks = (MasterGit() ~< CommitAt("commit feito por outra pessoa", "origin/master")
      ~ Pull("origin", "master") ~ Commit("commit no master") ~< CommitAt("mais um commit em origin", "origin/master")
      ~ Pull("origin", "master") ~ Commit("commit acima da mensagem de merge")).tasks
    new TaskGroup("Pegar commits de branches remotos (Pull)", "git.pull", descriptions, tasks : _*)
  }

  def workflow = {
    val tasks = (MasterGit() ~ Checkout("work", true) ~< ModifiedFile("arquivo") ~ Add("arquivo")
      ~ Commit("corrigidos problemas no arquivo") ~< UntrackedFile("outroArquivo") ~ Add("outroArquivo")
      ~ Commit("adicionado outroArquivo") ~ Checkout("master") ~ Merge("work") ~ Push("origin", "master")
      ~ Checkout("work") ~< CommitAt("commit de outra pessoa", "origin/master") ~ Commit("mais um commit")
      ~ Checkout("master") ~ Pull("origin", "master") ~ Checkout("work") ~ Rebase("master") ~ Checkout("master")
      ~ Merge("work") ~ Push("origin", "master")).tasks

    new TaskGroup("Git Workflow", "git.workflow", descriptions, tasks : _*)
  }

  def getDescription = "Git é uma ferramenta de controle de versão que tem crescido muito nos últimos anos. Este jogo cobre " +
    "os principais comandos e fluxos de trabalho com esta ferramenta."

  def getName = "Git"

  def path = "git"
}
