package vggames.regex.task

import com.google.common.base.Joiner
import vggames.task.status.Failed
import vggames.task.status.Faileds
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import scala.collection.JavaConverters._

@RunWith(classOf[JUnitRunner])
class FailedsSpec extends Specification {
  "the Faileds" should {
    val faileds = new Faileds()
    faileds.addOnlyJudgedFailed(new Failed("[aPattern] não dá match em [matchingTarget]"))
    faileds.addOnlyJudgedFailed(new Failed("[aPattern1] não dá match em [matchingTarget1]"))
    
    "show description to any faileds" in {
      Joiner.on("<br />").join(faileds asJava) must_== faileds.getReason()
    }

    "show description to all faileds" in {
      val testFaileds = new Faileds()
      testFaileds.addAll(faileds)
      Joiner.on("<br />").join(testFaileds asJava) must_== testFaileds.getReason()
    }
  }
}
