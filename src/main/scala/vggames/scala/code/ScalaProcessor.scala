package vggames.scala.code

import com.twitter.util.Eval
import java.security.Permission
import vggames.scala.specs.GameSpecification
import vggames.scala.tasks.judge.ExecutionFailure
import vggames.shared.task.JudgedTask
import vggames.scala.code.Wrappers._

class ScalaProcessor[T <: CodeRestrictions[_]](spec : GameSpecification[T]) {
  val className = "ExpressionRunner"
  val fullName = "scalagameunsafe.ExpressionRunner"

  def processCode(code : String) : JudgedTask = {
    val eval = new Eval(None)
    compile(code, eval)
    run(className, eval, code)
  }

  def run(className : String, eval : Eval, submittedCode : String) : JudgedTask = {
    val code = eval.findClass(fullName).newInstance.asInstanceOf[T]
    try {
      spec.run(code, submittedCode).judgement
    } catch {
      case t => { t.printStackTrace; new ExecutionFailure(t) }
    }
  }

  private def compile(code : String, eval : Eval) = {
    if (code.contains("finally") || code.contains("catch") ||
      code.contains(TaskRunSecurityManager.getClass.getSimpleName.replace("$", "")))
      throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    val wrapped = wrap(className, code, spec.extendsType, spec.runSignature, spec.afterCode)
    eval.compile(wrapped)
  }
}

object TaskRunSecurityManager extends SecurityManager {
  val unsafe = new ThreadLocal[Boolean]

  override def checkPermission(perm : Permission) = handlePermission(perm)
  override def checkPermission(perm : Permission, context : Object) = handlePermission(perm)

  def handlePermission(perm : Permission) =
    if (unsafe.get) throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
}

object Wrappers {
  def wrap(className : String, code : String, extendsType : String, runSignature : String, afterCode : String) = {
    "package scalagameunsafe\n" +
      "import vggames.scala.code._\n" +
      "class " + className + " extends " + extendsType + " {\n" +
      "  def run" + runSignature + " = {\n" +
      code + "\n" +
      afterCode + "\n" +
      "  }\n" +
      "}\n"
  }
}
