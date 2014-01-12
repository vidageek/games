package vggames.scala.specs.whileloop

import vggames.scala.specs.GameSpecification
import vggames.scala.code.RestrictedFunction0
import vggames.scala.specs.TestRun

class MaiorValorArray extends GameSpecification[RestrictedFunction0[Int]] {
  
  def runSignature = ":Int"

  def extendsType = "RestrictedFunction0[Int]"

  override def beforeCode = "val arr = Array(1,2,3)"
  
  override def afterCode = "maior"

  def challenge = """
Considere que j&aacute; existe um array <code>arr: Array[Int]</code> e encontre seu
maior valor, utilizando uma estrutura de repeti&ccedil;&atilde;o <code>while</code>.
Guarde o resultado em uma vari&aacute;vel chamada <code>maior</code>.
<br><br>
Obs. Ser&aacute; necess&aacute;rio declarar a vari&aacute;vel <code>maior</code>.
"""

  def run(code : Code, submittedCode : String)(implicit cases : TestRun) =
    "O seu código" should {
      "utilizar while para percorrer o array" in {
        submittedCode.contains("while") must beTrue
      }
      
      "encontrar o maior valor do array" in {
        code() must_== 3
      }
    }  
}
