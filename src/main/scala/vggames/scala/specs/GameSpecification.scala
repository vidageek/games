package vggames.scala.specs

import org.specs2.mutable.Specification

trait GameSpecification[T <: CodeRestrictions[_]] extends Specification {

  var code : T = _

  def challenge : String

  def runSignature : String

  def extendsType : String
}