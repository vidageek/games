package vggames.shared.task;

trait Task {

  def judge(challenge : String) : JudgedTask

  def getChallenge : String
}
