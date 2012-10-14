package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction2
import vggames.scala.specs.TestRun

class ComparacaoStrings extends GameSpecification[RestrictedFunction2[String, String, Boolean]] {

  def runSignature = "(a:String, b:String):Boolean"

  def extendsType = "RestrictedFunction2[String, String, Boolean]"

  def getChallenge = """Devolva <code>false</code> se a string <code>a</code> é <strong>menor</strong> que <code>b</code> e <code>true</code> caso contrário"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =

    "O seu código" should {

      """ devolver false quando comparar "abc" e "abd" """ in {
        code("abc", "abd") must beFalse
      }

      """ devolver true quando comparar "b" com "a" """ in {
        code("b", "a") must beTrue
      }

      """ devolver false quando "abc" com "abc" """ in {
        code("abc", "abc") must beFalse
      }
    }

}