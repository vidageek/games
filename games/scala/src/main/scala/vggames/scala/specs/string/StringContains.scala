package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1
import vggames.scala.specs.TestRun

class StringContains extends GameSpecification[RestrictedFunction1[String, Boolean]] {

  def runSignature = "(a:String):Boolean"

  def extendsType = "RestrictedFunction1[String, Boolean]"

  def challenge = """Devolva <code>true</code> se <code>a</code> contêm <code>"ara"</code> e <code>false</code> caso contrário"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {
      """ devolver true para "arara" """ in {
        code("arara") must beTrue
      }

      """ devolver true para "cara" """ in {
        code("cara") must beTrue
      }
      """ devolver false para "cobra" """ in {
        code("cobra") must beFalse
      }

    }
}