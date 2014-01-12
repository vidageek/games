package vggames.scala.specs.booleans

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class False extends GameSpecification[RestrictedFunction0[Boolean]] {

  def runSignature = ":Boolean"

  def extendsType = "RestrictedFunction0[Boolean]"

  def challenge = """Devolva <code>false</code>"""

  override def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ devolver false """ in {
        code() must beFalse
      }
    }
}