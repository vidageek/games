package vggames.regex

class MatcherTargets(matcherTargets : List[String]) {
  private val escaper = new Escaper

  def asHtml() : String = swapLastComma("<code>" + scapeTarges.mkString("</code>, <code>") + "</code>")

  def foreach = matcherTargets.foreach _

  private def scapeTarges() = escaper.applyAll(matcherTargets)

  private def swapLastComma(string : String) = string.replaceAll(",([^,]+)$", " e$1")
}
