package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction2

class ConcatStrings extends GameSpecification[RestrictedFunction2[String, String, String]] {

  def runSignature = "(a:String, b:String):String"

  def extendsType = "RestrictedFunction2[String, String, String]"

  def getChallenge = """Concatene duas Strings a e b"""

  "O seu código" should {
    """ concatenar "abc" com "def" e produzir "abcdef" """ in {
      code("abc", "def") must_== "abcdef"
    }

    """ concatenar "ban" com "ana" e produzir "banana" """ in {
      code("ban", "ana") must_== "banana"
    }
  }
}