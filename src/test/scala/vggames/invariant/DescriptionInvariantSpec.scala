package vggames.invariant

import java.util.regex.Pattern

import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

import net.vidageek.invariant.{FileData, Invariant, InvariantRunner}

@RunWith(classOf[InvariantRunner])
class DescriptionInvariantTest {

  @Invariant(affects = ".*\\.html", folder = "src/main/resources")
  def regexMustBeProperlyWritten(data : FileData) = {
    val matcher = Pattern.compile("(?i)(regex)[^\"]").matcher(data.getContent())
    while (matcher.find()) {
      assertTrue("Found invalid way of writing RegEx", "RegEx".equals(matcher.group(1)))
    }
  }
}
