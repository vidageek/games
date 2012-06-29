package vggames.regex.task

class MatcherTargets private (matcherTargets : List[String]) extends Iterable[String] {
  private val escaper = new Escaper
  private def scapeTarges() = escaper.applyAll(matcherTargets)

  def iterator() : Iterator[String] = matcherTargets.iterator

  def asHtml() : String = swapLastComma("<code>" + scapeTarges.mkString("</code>, <code>") + "</code>")

  private def swapLastComma(string : String) = {
    if (string contains ",")
      string.reverse.replaceFirst(",", "e ").reverse
    else
      string
  }
}

object MatcherTargets {
  def from(matchingTargets : String*) : MatcherTargets = new MatcherTargets(matchingTargets.toList)
  def from(matchingTargets : Array[String]) : MatcherTargets = new MatcherTargets(matchingTargets.toList)
}
