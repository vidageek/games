package vggames.regex

import java.util.regex.Pattern

class Escaper {
  private val shouldScapePatterns : Set[String] = Set("(?:(.*)(\\s)(.*))+")
  private val charsToReplace = Map[String, String]("\t" -> "-Tab-", "\b" -> "\\\\b",
    "\n" -> "-Quebra-de-Linha-", "\r" -> "\\\\r", "\f" -> "\\\\f", "\\" -> "\\\\",
    " " -> "-Espa&ccedil;o-", "\t" -> "-Tab-", "\r" -> "-Retorno-");

  def applyAll(words : List[String]) : List[String] = words.map(apply)

  def apply(word : String) : String = {
    if ("".equals(word)) {
      return "-Vazio-";
    }
    if (scape(word)) applyScapes(word) else word
  }

  def applyScapes(input : String) : String = charsToReplace.foldLeft(input)(
    (string, replaceRule) => string.replace(replaceRule._1, replaceRule._2))

  private def scape(input : String) : Boolean = thatMatchWith(input).isDefined

  private def thatMatchWith(input : String) : Option[String] = shouldScapePatterns.filter(input.matches(_)).headOption

}