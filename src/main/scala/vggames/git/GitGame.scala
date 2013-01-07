package vggames.git

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class GitGame(descriptions : Descriptions) extends Game {

  val tasks = new Tasks(
    init, add)

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
      (EmptyGit() ~< UntrackedFile("arquivo1.txt") ~< ModifiedFile("arquivo2.txt") ~ Add(".")).tasks

    new TaskGroup("Adicionando arquivos", "git.add", descriptions, tasks : _*)
  }

  def getDescription = "Um jogo muito legal para aprender Git"

  def getName = "Git"

}