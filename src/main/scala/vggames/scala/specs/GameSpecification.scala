package vggames.scala.specs

import org.specs2.mutable.Specification
import vggames.scala.code.CodeRestrictions

trait GameSpecification[T <: CodeRestrictions[_]] extends Specification {

  var code : T = _

  def challenge : String

  def runSignature : String

  def extendsType : String
}