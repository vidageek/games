package net.vidageek.games.regex.task

import java.util.Scanner
import net.vidageek.games.task.JudgedTask
import net.vidageek.games.task.Task

class MassNegateAndMatch(challenge: String, matcher: NegateAndMatch) extends Task {
  def judge(challenge: String): JudgedTask = matcher.judge(challenge)
  def getChallenge() = challenge
}

object MassNegateAndMatch {
  import MatcherTargets._
  
  def apply(fileName: String, challenge: String): MassNegateAndMatch = {
    val mustMatch = arrayFromFile("/mass/" + fileName + ".match")
    val cantMatch = arrayFromFile("/mass/" + fileName + ".not")
    val matcher = new NegateAndMatch(from(cantMatch), from(mustMatch))
    
    new MassNegateAndMatch(fileName, matcher)
  }
  
  private def arrayFromFile(filePath: String) = {
    val inputStream = classOf[MassNegateAndMatch].getResourceAsStream(filePath)
    if (inputStream == null) 
      Array[String]()
    else 
      new Scanner(inputStream).useDelimiter("$$").next().split("\n")
  }
}