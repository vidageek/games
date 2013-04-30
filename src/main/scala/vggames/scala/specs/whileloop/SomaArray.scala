package vggames.scala.specs.whileloop

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class SomaArray extends GameSpecification[RestrictedFunction0[Int]] {
  
  def runSignature = ":Int"

  def extendsType = "RestrictedFunction0[Int]"

  override def beforeCode = "val arr = Array(1,2,3)"
  
  override def afterCode = "soma"

  def getChallenge = """
Considere que j&aacute; existe um array <code>arr: Array[Int]</code> e some seus
elementos utilizando uma estrutura de repeti&ccedil;&atilde;o <code>while</code>.
Guarde o resultado em uma vari&aacute;vel chamada <code>soma</code>.
<br><br>
Obs. Ser&aacute; necess&aacute;rio declarar a vari&aacute;vel <code>soma</code>.
"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =
    "O seu código" should {
      """ somar os elementos do array """ in {
        code() must_== 6
      }
    }  
}
