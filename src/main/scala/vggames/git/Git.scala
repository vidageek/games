package vggames.git

case class Git(val _work : List[Commit]) {

  def ~(action : Action) = action(this)

}

object EmptyGit {
  def apply() = new Git(List())
}

trait Action {
  def apply(repo : Git) : Git
}

case class Commit(name : String) extends Action {
  def apply(repo : Git) =
    new Git(repo._work :+ this)
}

