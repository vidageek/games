package vggames.scala.specs.ifelse

import vggames.scala.code.RestrictedFunction2
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun
import vggames.scala.code.RestrictedFunction1

class If extends GameSpecification[RestrictedFunction1[Int, Any]] {

  def runSignature = "(numero:Int):Any"

  def extendsType = "RestrictedFunction1[Int, Any]"

  override def beforeCode = "val sinal = "
    
  override def afterCode = "sinal"

  def getChallenge = """Devolva o valor "negativo" se a constante numero for menor do que 0"""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve utilizar o comando if para fazer a verificação """ in {
        submittedCode.contains("if") must beTrue
      }

      """ deve devolver "negativo" se numero for menor do que 0 """ in {
        code(-1) must_== "negativo"
      }

      """ não deve devolver nada se numero for maior que 0 """ in {
        code(0) must_== ()
      }
    }
}