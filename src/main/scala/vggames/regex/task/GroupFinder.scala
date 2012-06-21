package vggames.regex.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GroupFinder(position : Int, pattern : Pattern) {

  def from(text : String) : String = {
    val matcher = pattern.matcher(text)
    if (matcher.find()) {
      return matcher.group(position)
    } else {
      throw new IllegalArgumentException("could not match text.")
    }
  }
}
