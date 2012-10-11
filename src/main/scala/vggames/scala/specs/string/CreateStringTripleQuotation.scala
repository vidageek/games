package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0

class CreateStringTripleQuotation extends GameSpecification[RestrictedFunction0[String]] {

  def runSignature = ":String"

  def extendsType = "RestrictedFunction0[String]"

  def getChallenge = "Crie uma string com o valor <code>Minha segunda String</code> usando <code>\"\"\"</code>"

  "O seu código" should {
    " criar a string \"\"\"Minha segunda String\"\"\"" in {
      code() must_== "Minha segunda String"
    }

    "utilizar seis aspas duplas" in {
      submittedCode must contain("\"\"\"")
    }
  }

}