package vggames.scala.specs

import vggames.scala.TaskRunSecurityManager

object Wrappers {

  def wrap(className : String, code : String, extendsType : String, runSignature : String) = {
    "package scalagameunsafe\n" +
      "import vggames.scala.specs._\n" +
      "class " + className + " extends " + extendsType + " {\n" +
      "  def run" + runSignature + " = {\n" +
      code + "\n" +
      "  }\n" +
      "}\n"
  }
}

sealed trait CodeRestrictions[+R] {
  def restrict[V >: R](code : => V) : V = {
    val old = System.getSecurityManager
    System.setSecurityManager(TaskRunSecurityManager)
    TaskRunSecurityManager.unsafe.set(true)
    try {
      code
    } finally {
      TaskRunSecurityManager.unsafe.set(false)
      System.setSecurityManager(old)
    }
  }
}

trait RestrictedFunction2[-T1, -T2, +R] extends Function2[T1, T2, R] with CodeRestrictions[R] {
  override def toString = "<restricted Function2>"

  override def apply(v1 : T1, v2 : T2) : R = {
    restrict(run(v1, v2))
  }

  def run(v1 : T1, v2 : T2) : R
}