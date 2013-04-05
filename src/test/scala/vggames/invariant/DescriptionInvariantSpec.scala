package vggames.invariant

import java.util.regex.Pattern
import org.junit.Assert.{ assertTrue, assertFalse }
import org.junit.runner.RunWith
import net.vidageek.invariant.{ FileData, Invariant, InvariantRunner }

@RunWith(classOf[InvariantRunner])
class DescriptionInvariantTest {

  @Invariant(affects = ".*\\.markdown", folder = "src/main/resources/desc")
  def regexMustBeProperlyWritten(data : FileData) = {
    val matcher = Pattern.compile("(?i)(regex)[^\"]").matcher(data.getContent())
    while (matcher.find()) {
      assertTrue("Found invalid way of writing RegEx", "RegEx".equals(matcher.group(1)))
    }
  }

  @Invariant(affects = ".*\\.markdown", folder = "src/main/resources/desc")
  def mustNotContainHtmlEntities(data : FileData) = {
    val matcher = Pattern.compile("&[^;]{0,10};").matcher(data.getContent())
    assertFalse("Found use of html entity on markdown file", matcher.find())
  }

  @Invariant(affects = ".*\\.markdown", folder = "src/main/resources/desc")
  def mustNotHtmlTags(data : FileData) = {
    if (!"match.back.markdown".equals(data.getName())) {
      val matcher = Pattern.compile("<[a-z]+").matcher(data.getContent())
      assertFalse("Found use of html tag on markdown file", matcher.find())
    }
  }
}
