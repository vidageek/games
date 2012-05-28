package net.vidageek.games.regex.task

import com.google.common.base.Joiner
import java.util.Collections
import scala.collection.JavaConverters._

class MatcherTargets private(matcherTargets: List[String]) extends java.lang.Iterable[String] {
  private val escape = new Escaper()
  private def scapeTarges() = escape.applyAll(matcherTargets asJava)

  def iterator(): java.util.Iterator[String] = Collections.unmodifiableCollection(matcherTargets asJava).iterator()

  def asHtml(): String = swapLastComma("<code>" + Joiner.on("</code>, <code>").join(scapeTarges()) + "</code>")

  private def swapLastComma(string: String) = {
    if (string contains ",")
      string.reverse.replaceFirst(",", "e ").reverse
    else 
      string
  }
}

object MatcherTargets {
  def from(matchingTargets: String*): MatcherTargets = new MatcherTargets(matchingTargets.toList)
  
  // workaround to suit some java callers - can probably be removed when those are ported to scala
  def from(matchingTargets: Array[String]): MatcherTargets = new MatcherTargets(matchingTargets.toList)
  def from(matchingTargets: String): MatcherTargets  = new MatcherTargets(Array(matchingTargets).toList)
}
