package vggames.html

import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup
import vggames.shared.ResourceDescription
import vggames.shared.GameEngine
import vggames.shared.task.TaskGroup

class HtmlGame extends GameEngine {

  override val tasks = new Tasks(
    textMetadata,
    links,
    images,
    paragraphAndLineBreak,
    orderedList,
    unorderedList,
    headings,
    form,
    inputs,
    moreInputs,
    table,
    pageStructure,
    contentStructure,
    nonSemantic,

    // extras
    definitionList,
    structureMetadata,
    includeCss,
    includeJs)

  // i, b, u, em, strong, del
  private def textMetadata = TaskGroup("Metadados em texto", "text.metadata")

  // a
  private def links = TaskGroup("Links", "text.link")

  // img
  private def images = TaskGroup("Imagens", "text.image")

  // p, br
  private def paragraphAndLineBreak = TaskGroup("Parágrafos e quebra de linha", "paragraph.linebreak")

  // ol, li
  private def orderedList = TaskGroup("Listas ordenadas", "list.ordered")

  // ul
  private def unorderedList = TaskGroup("Listas não ordenadas", "list.unordered")

  // h1-h6
  private def headings = TaskGroup("Cabeçalhos", "headings")

  // form
  private def form = TaskGroup("Form", "form")

  // type=[text|email|password|submit|textarea]
  private def inputs = TaskGroup("Inputs", "inputs")

  // type=[select|checkbox|radio|date|datetime|file|hidden]
  private def moreInputs = TaskGroup("Mais inputs", "inputs.more")

  // table, thead, tfoot, tr, th, td
  private def table = TaskGroup("Tabelas", "table")

  // html, head, body, title
  private def pageStructure = TaskGroup("Estrutura de uma página", "page.structure")

  // article, section, aside, nav
  private def contentStructure = TaskGroup("Estrutura do conteúdo", "content.structure")

  // div, spam
  private def nonSemantic = TaskGroup("Tags sem significado", "non.semantic")

  // dl, dt, dd
  private def definitionList = TaskGroup("Listas de definição", "list.definition")

  // meta
  private def structureMetadata = TaskGroup("Metadados da página", "page.metadata")

  // link
  private def includeCss = TaskGroup("Incluir Css", "include.css")

  // script
  private def includeJs = TaskGroup("Incluir Js", "include.js")

  def description = "Html Game"

  def name = "Html"

  def path = "html"

  override def resourceDescription = Some(ResourceDescription("text/html", "html", "html"))

}
