package vggames.scala.specs.list

import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun

class IntList extends GameSpecification[RestrictedFunction0[List[Int]]] {
  
  def runSignature = ":List[Int]"

  def extendsType = "RestrictedFunction0[List[Int]]"

  def getChallenge = """Crie uma <code>"lista"</code> de inteiros contendo os numeros <code>"1,2,3"</code>"""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve gerar uma lista de inteiros """ in {
        submittedCode.contains("List") must beTrue
      }

      """ deve devolver uma "lista de inteiros 1,2,3" """ in {
        code() must_== List(1,2,3)
      }

    }
  
}