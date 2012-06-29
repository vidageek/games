package vggames.regex.task;

import java.util.concurrent.ConcurrentHashMap
import java.util.regex.Pattern
import java.util.Map

class Escaper {
  private val shouldScapePatterns : Set[String] = Set("(?:(.*)(\\s)(.*))+")
  private val fromToCharacterReplace : Map[String, String] = new ConcurrentHashMap[String, String];

  fillShouldMapChar

  private def fillShouldMapChar() = {
    fromToCharacterReplace.put("\t", "-Tab-");
    fromToCharacterReplace.put("\b", "\\\\b");
    fromToCharacterReplace.put("\n", "-Quebra-de-Linha-");
    fromToCharacterReplace.put("\r", "\\\\r");
    fromToCharacterReplace.put("\f", "\\\\f");
    fromToCharacterReplace.put("\'", "\\\\'");
    fromToCharacterReplace.put("\\", "\\\\");
    fromToCharacterReplace.put(" ", "-Espa&ccedil;o-");
  }

  def applyAll(words : List[String]) : List[String] = {
    words.map(apply)
  }

  def apply(word : String) : String = {
    if ("".equals(word)) {
      return "-Vazio-";
    }
    if (scape(word)) applyScapes(word) else word
  }

  def applyScapes(input : String) : String = {
    val aRegex = thatMatchWith(input).getOrElse("");
    val compiledRegex = Pattern.compile(aRegex);
    val matcher = compiledRegex.matcher(input);
    var result = "";
    while (matcher.find()) {
      result += matcher.replaceAll(matcher.group(1) + fromToCharacterReplace.get(matcher.group(2)) + matcher.group(3));
    }
    return result;
  }

  private def scape(input : String) : Boolean = {
    thatMatchWith(input).isDefined
  }

  private def thatMatchWith(input : String) : Option[String] = shouldScapePatterns.filter(input.matches(_)).headOption

}