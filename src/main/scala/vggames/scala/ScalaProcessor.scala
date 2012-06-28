package vggames.scala

import com.twitter.util.Eval

class ScalaProcessor {
  def processCode[T](code : String) : T = {
    new Eval(None).apply[T](code)
  }
}
