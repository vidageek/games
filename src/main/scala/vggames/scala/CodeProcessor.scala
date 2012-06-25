package vggames.scala

import com.twitter.util.Eval

class CodeProcessor {
  def processCode(code: String): Boolean = {
    Eval[Boolean](code)
  }
}
