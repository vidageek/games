package vggames.scala.specs.ifelse

import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun

class IfElse extends GameSpecification[RestrictedFunction1[Int, String]] {

  def runSignature = "(numero:Int):String"

  def extendsType = "RestrictedFunction1[Int, String]"

  def challenge = """Devolva <code>"negativo"</code> se a constante <code>numero</code> 
    for menor do que 0, senão devolva <code>"positivo"</code>"""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve utilizar o comando if para fazer a verificação """ in {
        submittedCode.contains("if") must beTrue
      }
      
      """ deve utilizar o comando else para a condição oposta """ in {
        submittedCode.contains("else") must beTrue
      }

      """ deve devolver "negativo" se numero for menor do que 0 """ in {
        code(-1) must_== "negativo"
      }

      """ deve devolver "positivo" caso contrário """ in {
        code(0) must_== "positivo"
      }
    }
}