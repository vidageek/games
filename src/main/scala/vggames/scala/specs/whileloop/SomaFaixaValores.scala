package vggames.scala.specs.whileloop

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class SomaFaixaValores extends GameSpecification[RestrictedFunction0[Int]] {
  
  def runSignature = ":Int"

  def extendsType = "RestrictedFunction0[Int]"

  override def beforeCode = "val arr = Array(1,2,3)"
  
  override def afterCode = "soma"

  def getChallenge = """
Some todos os valores entre 1 e 100 utilizando uma estrutura de repeti&ccedil;&atilde;o
<code>while</code>. Guarde o resultado em uma vari&aacute;vel chamada <code>soma</code>.
<br><br>
Obs. Ser&aacute; necess&aacute;rio declarar a vari&aacute;vel <code>soma</code>.
"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =
    "O seu código" should {
      "utilizar while para percorrer todos os n&uacute;meros" in {
        submittedCode.contains("while") must beTrue
      }
      
      "somar todos os valores entre 1 e 100" in {
        code() must_== 5050
      }
    }  
}
