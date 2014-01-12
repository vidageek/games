package vggames.scala.specs.valvar

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class DefineVarInt extends GameSpecification[RestrictedFunction0[Int]] {

  def runSignature = ":Int"

  def extendsType = "RestrictedFunction0[Int]"

  override def afterCode = "numero = numero\n numero"

  def challenge = """Defina a variável chamada <code>numero</code> com o valor <code>314</code> """

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ definir a constante "val" """ in {
        code() must_== 314
      }
    }
}