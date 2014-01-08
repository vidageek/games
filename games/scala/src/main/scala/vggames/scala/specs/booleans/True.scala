package vggames.scala.specs.booleans

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class True extends GameSpecification[RestrictedFunction0[Boolean]] {

  def runSignature = ":Boolean"

  def extendsType = "RestrictedFunction0[Boolean]"

  def getChallenge = """Devolva <code>true</code>"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ devolver true """ in {
        code() must beTrue
      }
    }
}