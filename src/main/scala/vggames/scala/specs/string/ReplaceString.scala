package vggames.scala.specs.string

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction1

class ReplaceString extends GameSpecification[RestrictedFunction1[String, String]] {

  def runSignature = "(a:String):String"

  def extendsType = "RestrictedFunction1[String, String]"

  def getChallenge = """Substitua as ocorrências de <code>"aba"</code> em <code>a</code> por <code>"ebe"</code> """

  "O seu código" should {
    """ mudar "abaixa" para "ebeixa" """ in {
      code("abaixa") must_== "ebeixa"
    }

    """ não mudar "elefante" """ in {
      code("elefante") must_== "elefante"
    }
  }
}