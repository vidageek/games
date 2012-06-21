package vggames.regex.task;

import java.util.regex.Matcher
import vggames.shared.task.JudgedTask

trait GroupValidation {

  def judge(challenge : String, matcher : Matcher) : JudgedTask

}
