package vggames.meta;

import vggames.shared.task.JudgedTask
import vggames.shared.task.Task;
import vggames.shared.task.status.Ok


case class MetaTask(description : String) extends Task[Any] {
	
  def judge(challenge : String) : JudgedTask = new Ok();

  def getChallenge : String = description
  
  def resource = ""
	  
}