package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.TestRun

class SplitStrings extends GameSpecification[RestrictedFunction1[String, Array[String]]] {

  def runSignature = "(a:String):Array[String]"

  def extendsType = "RestrictedFunction1[String, Array[String]]"

  def getChallenge = """Quebre a string <code>a</code> nos espaços"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ quebrar a string "um dia" e produzir "um" e "dia" """ in {
        code("um dia") must_== Array("um", "dia")
      }

      """ não quebrar a string "banana" """ in {
        code("banana") must_== Array("banana")
      }
    }
}