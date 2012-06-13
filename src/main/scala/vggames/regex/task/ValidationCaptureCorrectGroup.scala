package vggames.regex.task;

import vggames.regex.task.MatcherTargets._

import java.util.regex.Matcher

import vggames.task.JudgedTask
import vggames.task.status.Failed
import vggames.task.status.Ok

class ValidationCaptureCorrectGroup(matchingTarget: String) extends GroupValidation {

	def judge(challenge: String, matcher: Matcher): JudgedTask = {
		if (!matchingTarget.equals(matcher.group(0))) {
			return new Failed("A regex " + from(challenge).asHtml() + " n&atilde;o reconhece a string "
					+ from(matchingTarget).asHtml())
		}
		new Ok()
	}

}
