package vggames.scala.actors

case class Run[V](code: () => V)
case class CodeRunTimeout(hash: Int)
