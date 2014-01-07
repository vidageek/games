package vggames.scala.specs.literal

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class RepresentString extends GameSpecification[RestrictedFunction0[String]] {

  def runSignature = "():String"

  def extendsType = "RestrictedFunction0[String]"

  def getChallenge = """Crie o texto "jogo legal!""""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """Definir o texto "jogo legal!"""" in {
        code() must_== "jogo legal!"
      }
      """Conter aspas duplas<code>"</code>""" in {
        submittedCode must contain("\"")
      }
    }

}