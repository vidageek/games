package vggames.scala.specs.options

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class NoneValue extends GameSpecification[RestrictedFunction0[Option[String]]] {

  def runSignature = "():Option[String]"

  def extendsType = "RestrictedFunction0[Option[String]]"

  def getChallenge = """Crie um valor Option para o valor null """

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =
    "O seu codigo" should {
      """ criar um valor Option para o valor null""" in {
        code() must_== None
      }
    }
}