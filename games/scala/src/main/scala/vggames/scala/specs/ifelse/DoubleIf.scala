package vggames.scala.specs.ifelse

import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun

class DoubleIf extends GameSpecification[RestrictedFunction1[Int, Any]] {

  def runSignature = "(numero:Int):Any"

  def extendsType = "RestrictedFunction1[Int, Any]"

  def getChallenge = """Devolva <code>"negativo"</code> se a constante <code>numero</code> 
    for menor do que 0, senão devolva <code>"positivo"</code> se <code>numero</code> for maior
    do que 0."""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve utilizar o comando if para fazer a verificação """ in {
        submittedCode.contains("if") must beTrue
      }

      """ deve devolver "negativo" se numero for menor do que 0 """ in {
        code(-1) must_== "negativo"
      }
      
      """ deve devolver "positivo" se numero for maior do que 0 """ in {
        code(1) must_== "positivo"
      }

      """ não deve devolver nada se numero for igual a 0 """ in {
        code(0) must_== ()
      }
    }
}