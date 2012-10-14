package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class CreateString extends GameSpecification[RestrictedFunction0[String]] {

  def runSignature = ":String"

  def extendsType = "RestrictedFunction0[String]"

  def getChallenge = """Crie uma string com o valor <code>Minha primeira String</code> usando <code>"</code>"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ criar a string "Minha primeira String"""" in {
        code() must_== "Minha primeira String"
      }

      "utilizar duas aspas duplas" in {
        submittedCode must contain("\"")
        submittedCode must not contain ("\"\"\"")
      }
    }

}