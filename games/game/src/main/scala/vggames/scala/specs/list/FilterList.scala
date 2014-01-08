package vggames.scala.specs.list

import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun
import vggames.scala.code.RestrictedFunction1

class FilterList extends GameSpecification[RestrictedFunction1[List[Int],List[Int]]] {
  
  def runSignature = "(lista:List[Int]):List[Int]"

  def extendsType = "RestrictedFunction1[List[Int],List[Int]]"

  def getChallenge = """Use o metodo <code>filter</code> de um <code>List</code> chamado <code>"lista"</code> contendo os numeros <code>"1,2,3,4,5"</code> para retornar um <code>List</code> contendo valores menores que 4"""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve usar o metodo <code>filter</code> """ in {
        submittedCode.contains("filter") must beTrue
      }

      """ deve devolver uma "lista de inteiros com os valores 1,2,3" """ in {
        code(List(1,2,3,4,5)) must_== List(1,2,3)
      }

    }
  
}