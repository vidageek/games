package vggames.scala

import com.twitter.util.Eval

class CodeProcessor {
  def processCode(code : String) : Boolean = {
    new Eval(None).apply[Boolean](code)
  }
}
