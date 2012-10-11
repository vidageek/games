package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1

class SubStrings extends GameSpecification[RestrictedFunction1[String, String]] {

  def runSignature = "(a:String):String"

  def extendsType = "RestrictedFunction1[String, String]"

  def getChallenge = """Devolva um pedaço da string <code>a</code> entre os caracteres <code>2</code> e <code>5</code>"""

  "O seu código" should {
    """ cortar "cachorro" e produzir "cho" """ in {
      code("cachorro") must_== "cho"
    }

    """ cortar "elefante" e produzir "efa" """ in {
      code("elefante") must_== "efa"
    }

  }
}