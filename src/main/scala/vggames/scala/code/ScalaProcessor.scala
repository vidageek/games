package vggames.scala.code

import com.twitter.util.{ Eval => TwitterEval }
import java.security.Permission
import vggames.scala.specs.GameSpecification
import vggames.scala.tasks.judge.ExecutionFailure
import vggames.shared.task.JudgedTask
import vggames.scala.code.Wrappers._
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import java.io.File
import java.security.MessageDigest
import scala.math.BigInt

class ScalaProcessor[T <: CodeRestrictions[_]](spec : GameSpecification[T]) {
  val className = "ExpressionRunner"
  val fullName = "scalagameunsafe.ExpressionRunner"

  def processCode(code : String) : JudgedTask = {

    val klass = compile(code)
    run(className, klass, code)
  }

  def run(className : String, klass : Class[_], submittedCode : String) : JudgedTask = {
    val code = klass.newInstance.asInstanceOf[T]
    try {
      spec.run(code, submittedCode).judgement
    } catch {
      case t => { t.printStackTrace; new ExecutionFailure(t) }
    }
  }

  def hash(s : String) =
    BigInt(MessageDigest.getInstance("MD5").digest(s.getBytes)).toString(16).replace("-", "")

  private def compile(code : String) = {
    if (code.contains("finally") || code.contains("catch") ||
      code.contains(TaskRunSecurityManager.getClass.getSimpleName.replace("$", "")))
      throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    val codeHash = hash(code)
    val wrapped = wrap(className + codeHash, code, spec.extendsType, spec.runSignature, spec.afterCode)
    Compile(_.toClass(wrapped, fullName + codeHash))
  }
}

object Compile {
  val pool = ListBuffer[Eval]()

  def compiler() = synchronized { pool.headOption.getOrElse(new Eval(None)) }

  def discard(eval : Eval) = synchronized { pool += eval }

  def apply(f : Eval => Class[_]) = {
    val eval = compiler()
    try {
      f(eval)
    } catch {
      case t => { t.printStackTrace; throw t }

    } finally {
      discard(eval)
    }
  }
}

class Eval(file : Option[File]) extends TwitterEval(file) {

  def toClass(code : String, name : String) = {
    compile(code)
    findClass(name)
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
