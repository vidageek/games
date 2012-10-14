package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.TestRun

class ConcatStringWithConstant extends GameSpecification[RestrictedFunction1[String, String]] {

  def runSignature = "(a:String):String"

  def extendsType = "RestrictedFunction1[String, String]"

  def getChallenge = """Concatene a string <code>a</code> com a constante <code>"taz"</code>"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ concatenar "abc" com "taz" e produzir "abctaz" """ in {
        code("abc") must_== "abctaz"
      }

      """ concatenar "capa" com "taz" e produzir "capataz" """ in {
        code("capa") must_== "capataz"
      }
    }
}