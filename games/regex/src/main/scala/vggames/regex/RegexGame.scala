package vggames.regex

import vggames.shared.task.{ Descriptions, TaskGroup, Tasks }
import vggames.shared.GameEngine

class RegexGame extends GameEngine {
  override val tasks = new Tasks(
    addCharsExercises,
    addCharClassesExercises,
    addOpositeCharClassExercises,
    addPipeOperator,
    addOperatorsExercises,
    addCaptureGroupExercises,
    addBackReferencesExercises,
    addAnchoringExercises,
    addModesExercises,
    addRealWorldRegexes)

  private def addCharsExercises =
    new TaskGroup("Reconhecimento de Letras", "match.chars",
      new Match("a"),
      new Match("abc"),
      new Match("\\"),
      new Match("$"),
      new Match("abcdefg12345"),
      new Match("AbCdEfG6"),
      new Match("ab$cd^Ef\\G1"),
      new Match(""),
      new Match(" "),
      new Match("\n"),
      new Match("\t"),
      new Match("\f"),
      new Match("\r"))

  private def addCharClassesExercises =
    new TaskGroup("Classes de Caracteres", "match.chars.classes",
      new Match(List("a", "b")),
      new Match(List("ad", "bd")),
      new Match(List("a", "b", "c", "A", "B", "C", "D")),
      new Match(List("0", "1", "2")),
      new Match(List("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "x", "w", "y", "z")),
      new Match(List("1", "4", "5")),
      new Match(List("1a", "4a", "5a")),
      new Match(List("1", "4", "5", "a")),
      new Match(List(" ", "\t", "\n", "\f", "\r")),
      new Match(List(" a", "\ta", "\na")),
      new Match(List(" ", "\t", "\n", "a")),
      new Match(List("a", "b", "9")),
      new Match(List("ap", "bp", "9p")),
      new Match(List("a", "B", "9", "$", "\t", " ")))

  private def addOpositeCharClassExercises =
    new TaskGroup("Oposto de uma Classe de Caracteres", "match.negate",
      new NegateAndMatch(List("a", "b"), List("c", "d")),
      new NegateAndMatch(List("ad", "bd", "cd"), List("dd", "ed")),
      new NegateAndMatch(List("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), List(" ", "a")),
      new NegateAndMatch(List("1a", "4a", "5a"), List(" a", "$a")),
      new NegateAndMatch(List("1", "4", "5", "a"), List(" ", "b")),
      new NegateAndMatch(List("\t", "\n", "\f", "\r"), List("A", "w")),
      new NegateAndMatch(List(" a", "\ta", "\na"), List("ca", "#a")),
      new NegateAndMatch(List(" ", "\t", "\n", "a"), List("Z", "A")),
      new NegateAndMatch(List("a", "B", "9"), List(" ", "$")),
      new NegateAndMatch(List("ap", "Bp", "9p"), List("$p", "#p")))

  private def addPipeOperator =
    new TaskGroup("Múltiplos Padrões", "match.pipe",
      new NegateAndMatch(List("ab", "ba"), List("a", "b")),
      new NegateAndMatch(List("ba", "baa"), List("aa", "bb", "ab")))

  private def addOperatorsExercises =
    new TaskGroup("Operadores de Repetição", "match.operators",
      new NegateAndMatch("b", List("", "a")),
      new NegateAndMatch("aa", List("", "a", "b")),
      new NegateAndMatch(List("", "aaaaaaaaab"), List("a", "aa", "aaaaaaaaa")),
      new NegateAndMatch(List("", "abcdcba"), List("a", "abc", "cbaabc", "aabbcc")),
      new NegateAndMatch(List("abcdcba"), List("", "a", "abc", "cbaabc", "aabbcc")),
      new NegateAndMatch(List("aaaaaaaaab"), List("", "a", "aa", "aaaaaaaaa")),
      new NegateAndMatch(List("a", "aa"), "aaa"),
      new NegateAndMatch(List("a", "ab", "abca"), List("abc", "cba")))

  private def addCaptureGroupExercises =
    new TaskGroup("Grupos de Captura", "match.capture",
      new CaptureGroup("Nome: asdrubal", "asdrubal"),
      new CaptureGroup("Nome: asdrubal Sobrenome: solito", "asdrubal", "solito"),
      new CaptureGroup("Carambolas", "Carambolas", "Cara", "bolas"),
      new CaptureGroup("pasta de dente", "pasta de", "past", "de", "dente", "ente"))

  private def addBackReferencesExercises =
    new TaskGroup("Back References", "match.back",
      MassNegateAndMatch("back/odd", "Qual RegEx reconhece todas as sequ&ecirc;ncias &iacute;mpares de <code>c</code>?"),
      new NegateAndMatch("[a]abc[/b]", List("[a]abc[/a]", "[b]def[/b]")))

  private def addAnchoringExercises =
    new TaskGroup("Ancoras", "match.anchor",
      new NegateAndFind(List("/blog", "/blog/1abc"), List("/blog/1", "/blog/2")),
      new NegateAndFind("/blog/2", "/blog"),
      new NegateAndFind("a/blog/", "/blog/"),
      new NegateAndFind("blogueiro", "blog"))

  private def addModesExercises =
    new TaskGroup("Modos da RegEx", "match.modes",
      MassNegateAndMatch("modes/regex", "Qual RegEx &eacute; capaz de reconhecer todas as variações de escrita de RegEx" +
        " (regex, Regex, ...)?"),
      new Match(List("\nabcd", "a\nbcd", "ab\ncd", "abc\nd", "abcd\n")))

  private def addRealWorldRegexes =
    new TaskGroup("RegEx no Mundo Real", "match.real.world",
      MassNegateAndMatch("real/log", "Qual RegEx &eacute; capaz de reconhecer as linhas geradas &agrave;s 17 " +
        "horas de um log cujas mensagems parecem com </code>17:06:46,632  mensagem qualquer</code>"),
      MassNegateAndMatch("real/blog.urls", "Qual RegEx &eacute; capaz de reconhecer urls de blog como /blog/2012/03/12/post?"),
      MassNegateAndMatch("real/email", "Qual RegEx &eacute; capaz de reconhecer emails como games@vidageek.net?"),
      MassNegateAndMatch("real/dates", "Qual RegEx &eacute; capaz de reconhecer datas como 1/1/1970?"),
      MassNegateAndMatch("real/ipV4", "Qual RegEx &eacute; capaz de reconhecer IPs?"))

  def getDescription = "Expressão Regular é uma excelente forma de encontrar padrões em textos, mas a sua sintaxe é um pouco obscura. " +
    "Com esses exercícios você conseguirá não apenas entender como RegEx funciona, mas será capaz de utilizá-la."

  def getName = "RegEx"

  def path = "regex"
}