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
    contentStructure,
    nonSemantic,

    pageStructure,
    // extras
    definitionList,
    structureMetadata,
    includeCss,
    includeJs)

  private def textMetadata = TaskGroup("Metadados em texto", "text.metadata",
    SimpleTag("i"),
    SimpleTag("b"),
    SimpleTag("u"),
    SimpleTag("em"),
    SimpleTag("del"))

  private def links = TaskGroup("Links", "text.link",
    SimpleTag("a"),
    SimpleTag("a", "href" -> "http://www.google.com"))

  private def images = TaskGroup("Imagens", "text.image",
    EmptyTag("img", "alt" -> "texto alternativo", "src" -> "/static/html/html5.png"))

  private def paragraphAndLineBreak = TaskGroup("Parágrafos e quebra de linha", "paragraph.linebreak",
    SimpleTag("p"),
    EmptyTag("br"))

  private def orderedList = TaskGroup("Listas ordenadas", "list.ordered",
    EmptyTag("ol"),
    SimpleTag("li"))

  private def unorderedList = TaskGroup("Listas não ordenadas", "list.unordered",
    EmptyTag("ul"))

  private def headings = TaskGroup("Cabeçalhos", "headings",
    SimpleTag("h1"),
    SimpleTag("h2"),
    SimpleTag("h3"),
    SimpleTag("h4"),
    SimpleTag("h5"),
    SimpleTag("h6"))

  private def form = TaskGroup("Form", "form",
    EmptyTag("form"),
    EmptyTag("form", "action" -> "/busca"),
    EmptyTag("form", "method" -> "post"),
    EmptyTag("form", "method" -> "get", "action" -> "/busca"))

  private def inputs = TaskGroup("Inputs", "inputs",
    EmptyTag("input"),
    EmptyTag("input", "name" -> "nome"),
    EmptyTag("input", "tag-input-name-type-text", "name" -> "nome", "type" -> "text"),
    EmptyTag("input", "name" -> "nome", "value" -> "conteudo"),
    EmptyTag("input", "name" -> "nome", "placeholder" -> "digite aqui"),
    EmptyTag("input", "tag-input-name-type-email", "name" -> "nome", "type" -> "email"),
    EmptyTag("input", "tag-input-name-type-password", "name" -> "nome", "type" -> "password"),
    EmptyTag("input", "tag-input-name-type-submit", "name" -> "nome", "type" -> "submit"),
    EmptyTag("input", "tag-input-name-type-submit-value", "name" -> "nome", "type" -> "submit", "value" -> "clique aqui"),
    SimpleTag("textarea"),
    SimpleTag("textarea", "name" -> "nome"),
    SimpleTag("textarea", "name" -> "nome", "cols" -> "10"),
    SimpleTag("textarea", "name" -> "nome", "rows" -> "10"),
    SimpleTag("textarea", "name" -> "nome", "cols" -> "15", "rows" -> "15"))

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
