package vggames.scala.specs.valvar

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class DefineValInt extends GameSpecification[RestrictedFunction0[Int]] {

  def runSignature = ":Int"

  def extendsType = "RestrictedFunction0[Int]"

  override def afterCode = "numero"

  def challenge = """Defina a constante chamada <code>numero</code> com o valor <code>123</code> """

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ definir a constante 123 chamada numero """ in {
        code() must_== 123
      }
    }
}