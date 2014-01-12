package vggames.scala.specs.ifelse

import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun

class If extends GameSpecification[RestrictedFunction1[Int, Any]] {

  def runSignature = "(numero:Int):Any"

  def extendsType = "RestrictedFunction1[Int, Any]"

  def challenge = """Devolva <code>"negativo"</code> se a constante <code>numero</code> 
    for menor do que 0"""

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