package vggames.scala.specs.booleans

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0

class False extends GameSpecification[RestrictedFunction0[Boolean]] {

  def runSignature = ":Boolean"

  def extendsType = "RestrictedFunction0[Boolean]"

  def getChallenge = """Devolva <code>false</code>"""

  "O seu código" should {
    """ devolver false """ in {
      code() must beFalse
    }
  }
}