Exemplo tirado da classe [MathTaskSpec.scala][1]

<pre>package vggames.math;


import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.task.status.Ok
import vggames.shared.task.status.Failed


@RunWith(classOf[JUnitRunner])
class MathTaskSpec extends Specification {
  
  "MathTask" should {
    
    "sum 1+1 == 2?" in {
      new MathTask(1,1,"+").judge("2") must_==Ok()
    }
    
    "sum 1+1 != 3?" in {
      new MathTask(1,1,"+").judge("3") must_==Failed("Resposta errada, tente novamente!")
    }
    
    "sub 10-5 == 5?" in {
      new MathTask(10,5,"-").judge("5") must_==Ok()
    }
    
    "mult 1*0 == 0?" in {
      new MathTask(1,0,"*").judge("0") must_==Ok()
    }
  }
}

[1]:https://github.com/vidageek/games/blob/master/src/test/scala/vggames/math/MathTaskSpec.scala