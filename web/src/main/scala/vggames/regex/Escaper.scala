package vggames.regex

import java.util.regex.Pattern

class Escaper {
  private val charsToReplace = Map[String, String]("\t" -> "-Tab-",
    "\n" -> "-Quebra-de-Linha-", "\f" -> "-Quebra-de-P&aacute;gina-",
    " " -> "-Espa&ccedil;o-", "\t" -> "-Tab-", "\r" -> "-Retorno-");

  def applyAll(words : List[String]) : List[String] = words.map(apply)

  def apply(word : String) : String = {
    if ("".equals(word)) {
      return "-Vazio-";
    }
    applyScapes(word)
  }

  def applyScapes(input : String) : String = charsToReplace.foldLeft(input)(
    (string, replaceRule) => string.replace(replaceRule._1, replaceRule._2))

}