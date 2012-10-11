package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction2

class ComparacaoStrings extends GameSpecification[RestrictedFunction2[String, String, Boolean]] {

  def runSignature = "(a:String, b:String):Boolean"

  def extendsType = "RestrictedFunction1[String, String, Boolean]"

  def getChallenge = """Devolva <code>false</code> se a string a é <strong>menor</strong> que b e <code>true</code> caso contrário"""

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