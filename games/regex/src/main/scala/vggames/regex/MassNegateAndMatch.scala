package vggames.regex

import java.util.Scanner
import vggames.shared.task.{ Task, JudgedTask }
import scala.io.Source

class MassNegateAndMatch(challenge : String, matcher : NegateAndMatch) extends Task {
  def judge(challenge : String) : JudgedTask = matcher.judge(challenge)
  def getChallenge = challenge
  def resource = ""
}

object MassNegateAndMatch {

  def apply(fileName : String, challenge : String) : MassNegateAndMatch = {
    val mustMatch = fromFile("/mass/" + fileName + ".match")
    val cantMatch = fromFile("/mass/" + fileName + ".not")
    val matcher = new NegateAndMatch(cantMatch, mustMatch)

    new MassNegateAndMatch(challenge, matcher)
  }

  private def fromFile(filePath : String) = {
    Source.fromInputStream(classOf[MassNegateAndMatch].getResourceAsStream(filePath)).getLines.toSeq
  }
}