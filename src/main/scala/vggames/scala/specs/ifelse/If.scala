package vggames.scala.specs.ifelse

import vggames.scala.code.RestrictedFunction2
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun
import vggames.scala.code.RestrictedFunction1

class If extends GameSpecification[RestrictedFunction1[Int, String]] {

  def runSignature = "(numero:Int):String"

  def extendsType = "RestrictedFunction1[Int, String]"

  override def afterCode = "sinal"

  def getChallenge = """Defina a variável <code>sinal</code> com o valor "positivo". Depois,
    atribua a <code>sinal</code> o valor "negativo" se a constante numero for menor do que 0"""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve utilizar o comando if para fazer a verificação """ in {
        submittedCode.contains("if") must beTrue
      }

      """ atribuir o valor negativo para a variável sinal se numero for menor do que 0 """ in {
        code(-1) must_== "negativo"
      }

      """ manter o valor positivo para a variável sinal se numero for maior que 0 """ in {
        code(0) must_== "positivo"
      }
    }
}