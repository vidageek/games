package vggames.scala.specs.valvar

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class DefineValString extends GameSpecification[RestrictedFunction0[String]] {

  def runSignature = ":String"

  def extendsType = "RestrictedFunction0[String]"

  override def afterCode = "valor"

  def getChallenge = """Defina a constante chamada <code>valor</code> com o valor <code>"val"</code> """

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ definir a constante "val" chamada valor """ in {
        code() must_== "val"
      }
    }
}