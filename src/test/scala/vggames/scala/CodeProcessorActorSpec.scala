package vggames.scala

import akka.actor.ActorSystem
import akka.dispatch.Await
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Duration
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CodeProcessorActorSpec extends Specification {
  "the code processor actor" should {
    implicit val system = ActorSystem("test-actor-system")
    
    "get a Compile message and reply with the result of the compiled code" in {
      val testActor = TestActorRef[CodeProcessorActor]
      val future = testActor.ask(Compile("true"))(Duration(5 ,"seconds")).mapTo[CompilationResult]
      
      val result = Await.result(future, Duration(5, "seconds"))
      result.ok must beTrue
    }
  }
}
