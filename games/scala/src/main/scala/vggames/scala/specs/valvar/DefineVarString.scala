package vggames.scala.specs.valvar

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class DefineVarString extends GameSpecification[RestrictedFunction0[String]] {

  def runSignature = ":String"

  def extendsType = "RestrictedFunction0[String]"

  override def afterCode = "valor = valor\n valor"

  def challenge = """Defina a variavel chamada <code>valor</code> com o valor <code>"var"</code> """

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ definir a variável "var" chamada valor """ in {
        code() must_== "var"
      }
    }
}