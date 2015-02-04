package vggames.html

import vggames.shared.task.Descriptions
import vggames.shared.task.Markdown
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
    headings,
    orderedList,
    unorderedList,
    form,
    inputs,
    textarea,

    table,
    contentStructure,
    nonSemantic,
    pageStructure,

    moreInputs,
    definitionList,
    structureMetadata,
    includeCss,
    includeJs)

  private def textMetadata = TaskGroup("Metadados em texto", "text.metadata",
    SimpleTag("em"),
    SimpleTag("strong"),
    SimpleTag("del"),
    TextToHtml("em"),
    TextToHtml("strong"),
    TextToHtml("del"))

  private def links = TaskGroup("Links", "text.link",
    SimpleTag("a"),
    SimpleTag("a", "href" -> "http://www.google.com"),
    TextToHtml("wikipedia"),
    TextToHtml("games"))

  private def images = TaskGroup("Imagens", "text.image",
    EmptyTag("img", "alt" -> "texto alternativo", "src" -> "/static/html/html5.png"))

  private def paragraphAndLineBreak = TaskGroup("Parágrafos e quebra de linha", "paragraph.linebreak",
    SimpleTag("p"),
    EmptyTag("br"))

  private def headings = TaskGroup("Cabeçalhos", "headings",
    SimpleTag("h1"),
    SimpleTag("h2"),
    SimpleTag("h3"),
    SimpleTag("h4"),
    SimpleTag("h5"),
    SimpleTag("h6"),
    TextToHtml("cabecalhos"))

  private def orderedList = TaskGroup("Listas ordenadas", "list.ordered",
    EmptyTag("ol"),
    SimpleTag("li"),
    TextToHtml("lista.chegada"))

  private def unorderedList = TaskGroup("Listas não ordenadas", "list.unordered",
    EmptyTag("ul"),
    TextToHtml("lista.compras"),
    TextToHtml("html5"))

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
    EmptyTag("input", "tag-input-name-type-submit-value", "name" -> "nome", "type" -> "submit", "value" -> "clique aqui"))

  private def textarea = TaskGroup("Label e Textarea", "textarea",
    SimpleTag("label"),
    SimpleTag("label", "for" -> "inputId"),
    SimpleTag("textarea"),
    SimpleTag("textarea", "name" -> "nome"),
    SimpleTag("textarea", "name" -> "nome", "cols" -> "10"),
    SimpleTag("textarea", "name" -> "nome", "rows" -> "10"),
    SimpleTag("textarea", "name" -> "nome", "cols" -> "15", "rows" -> "15"))
    //TextToHtml("form.login"),
    //TextToHtml("form.cadastro"),
    //TextToHtml("form.feedback"))

  private def table = TaskGroup("Tabelas", "table",
    EmptyTag("table"),
    EmptyTag("thead") withContext Table,
    EmptyTag("tfoot") withContext Table,
    EmptyTag("tr") withContext Table,
    SimpleTag("th") withContext Tr,
    SimpleTag("th", "colspan" -> "2") withContext Tr,
    SimpleTag("th", "rowspan" -> "3") withContext Tr,
    SimpleTag("th", "colspan" -> "3", "rowspan" -> "4") withContext Tr,
    SimpleTag("td") withContext Tr,
    SimpleTag("td", "colspan" -> "2") withContext Tr, 
    SimpleTag("td", "rowspan" -> "3") withContext Tr,
    SimpleTag("td", "colspan" -> "3", "rowspan" -> "4") withContext Tr
    )

  // article, section, aside, nav
  private def contentStructure = TaskGroup("Estrutura do conteúdo", "content.structure",
    SimpleTag("article"),
    SimpleTag("section"),
    SimpleTag("aside"),
    SimpleTag("nav")
    )

  // div, span
  private def nonSemantic = TaskGroup("Tags sem significado", "non.semantic",
    SimpleTag("div"),
    SimpleTag("span")
    )

  // html, head, body, title
  private def pageStructure = TaskGroup("Estrutura de uma página", "page.structure",
    EmptyTag("html") withContext Doctype,
    EmptyTag("head") withContext Html,
    SimpleTag("title") withContext Head,
    SimpleTag("body") withContext Html
    )

  // type=[checkbox|radio|date|datetime|file|hidden] & select
  private def moreInputs = TaskGroup("Mais inputs", "inputs.more")

  // dl, dt, dd
  private def definitionList = TaskGroup("Listas de definição", "list.definition")

  // meta
  private def structureMetadata = TaskGroup("Metadados da página", "page.metadata")

  // link
  private def includeCss = TaskGroup("Incluir Css", "include.css")

  // script
  private def includeJs = TaskGroup("Incluir Js", "include.js")

  def description = Markdown("""Html é uma linguagem que foi criada para acrescentarmos informações à texto de forma que um computador seja capaz de entender o significado das estruturas do texto.

Isso é necessário porque, quando olhamos para textos como:

    Comprar:
        - batata
        - cenouras
        - frutas

nós sabemos que isso é uma lista, mas um computador não sabe. E como ele não sabe, ele não consegue automatizar tarefas para nós (imagine a web sem links, você tendo que copiar a colar todas as urls que quiser visitar).

Hoje isso vai muito mais longe. Ferramentas como buscadores e outros analizam as nossas páginas para identificar, relacionar e tentar apresentar o melhor resultado para perguntas. Sem essa informação, isso seria praticamente impossível.

Esse jogo cobre as principais tags e atributos de Html 5""")

  override def resourceDescription = Some(ResourceDescription("text/html", "html", "html"))

}
