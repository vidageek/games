package vggames.scala.specs.ifelse

import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun

class DoubleIfElse extends GameSpecification[RestrictedFunction1[Int, String]] {

  def runSignature = "(numero:Int):String"

  def extendsType = "RestrictedFunction1[Int, String]"

  def challenge = """Devolva <code>"negativo"</code> se a constante <code>numero</code> 
    for menor do que 0, senão devolva <code>"positivo"</code> se <code>numero</code> for maior
    do que 0. Senão, devolva <code>"neutro"</code>"""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve utilizar o comando if para fazer a verificação """ in {
        submittedCode.contains("if") must beTrue
      }
      
      """ deve utilizar o comando else para a condição oposta às anteriores""" in {
        submittedCode.contains("else") must beTrue
      }

      """ deve devolver "negativo" se numero for menor do que 0 """ in {
        code(-1) must_== "negativo"
      }

      """ deve devolver "positivo" se numero for maior do que 0 """ in {
        code(1) must_== "positivo"
      }
      
      """ deve devolver "neutro" caso contrário """ in {
        code(0) must_== "neutro"
      }
    }
}