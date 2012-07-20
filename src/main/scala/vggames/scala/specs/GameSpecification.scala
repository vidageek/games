package vggames.scala.specs

import org.specs2.mutable.Specification

trait GameSpecification[T <: CodeRestrictions[_]] extends Specification {

  def challenge : String

  def wrap(classname : String, code : String) : String

  var code : T = _

}