package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.TestRun

class StringLength extends GameSpecification[RestrictedFunction1[String, Int]] {

  def runSignature = "(a:String):Int"

  def extendsType = "RestrictedFunction1[String, Int]"

  def challenge = """Devolva o tamanho da String <code>a</code>"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {

      """ devolver 5 para a string "arara"""" in {
        code("arara") must_== 5
      }

      """ devolver 7 para a string "bolacha"""" in {
        code("bolacha") must_== 7
      }

      """ devolver 8 para a string "cachorro"""" in {
        code("cachorro") must_== 8
      }
    }
}