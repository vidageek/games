package vggames.scala.specs.valvar

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class ReassignToVar extends GameSpecification[RestrictedFunction0[String]] {

  def runSignature = ":String"

  def extendsType = "RestrictedFunction0[String]"

  def challenge = """Defina a variável rio com o valor "tietê" e depois atribua a ela o valor "pinheiros" """

  override def afterCode = "rio"

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ definir a variavel rio """ in {
        submittedCode must contain("var")
      }

      """ atribuir o valor tietê à rio """ in {
        submittedCode must contain("tietê")
      }

      """ atribuir o valor prinheiros à rio """ in {
        code() must_== "pinheiros"
      }
    }
}