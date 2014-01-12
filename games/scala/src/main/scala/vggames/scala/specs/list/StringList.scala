package vggames.scala.specs.list

import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.GameSpecification
import vggames.scala.specs.TestRun

class StringList extends GameSpecification[RestrictedFunction0[List[String]]] {
  
  def runSignature = ":List[String]"

  def extendsType = "RestrictedFunction0[List[String]]"

  def challenge = """Crie uma <code>"lista"</code> de strings contendo as frutas <code>"abacaxi, laranja, abacate"</code>"""

  override def run(code: Code, submittedCode: String)(implicit cases: TestRun) =

    "O seu código" should {
      """ deve gerar uma lista de strings """ in {
        submittedCode.contains("List") must beTrue
      }

      """ deve devolver uma "lista de strings abacaxi, laranja, abacate" """ in {
        code() must_== List("abacaxi", "laranja", "abacate")
      }

    }
  
}