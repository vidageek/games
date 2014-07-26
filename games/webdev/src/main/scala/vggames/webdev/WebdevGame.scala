package vggames.webdev

import vggames.shared.task.{ Tasks, TaskGroup }
import vggames.shared.GameEngine
import vggames.shared.task.TaskGroup
import vggames.shared.task.Task
import vggames.shared.task.status.Ok

class WebdevGame extends GameEngine {

  val tasks = new Tasks(
    design ++
      html ++
      siteGenerator ++
      css ++
      siteGeneratorCss ++
      js ++
      codeReview : _*)

  private def design = Seq(
    new GithubProject("Setup inicial", "design.setup"),
    new GithubProject("Tartaruga", "design.logo.turtle"),
    new GithubProject("Tartaruga com obstáculos", "design.logo.obstacles"),
    new GithubProject("Imutabilidade", "design.imutability"),
    new GithubProject("Mapear coleções", "design.collection.map"),
    new GithubProject("Filtrar coleções", "design.collection.filter"),
    new GithubProject("Testes e TDD", "design.test.tdd"),
    new GithubProject("Conversão de Linguagem (__, // e **)", "design.markdown.text.decoration"),
    new GithubProject("Conversão de Linguagem (imagens e links)", "design.markdown.image.link"),
    new GithubProject("Conversão de Linguagem (lista)", "design.markdown.list"),
    new GithubProject("Api de Requisições web", "design.request.api"))

  private def html = Seq(
    new GithubProject("Página de autores", "html.authors"),
    new GithubProject("Uma receita", "html.recipe"),
    new GithubProject("Um post de um blog", "html.post"),
    new GithubProject("Uma tabela", "html.table"),
    new GithubProject("Uma página completa", "html.page"))

  private def siteGenerator = Seq(
    new GithubProject("Um gerador de sites", "project.generator"))

  private def css = Seq(
    new GithubProject("Uma página com formulário", "css.form"),
    new GithubProject("A página de um blog", "css.blog"),
    new GithubProject("Uma página com menu", "css.menu"),
    new GithubProject("Outra página com menu", "css.other.menu"))

  private def siteGeneratorCss = Seq(
    new GithubProject("Melhorar a cara do site genrado", "project.generator.css"),
    new GithubProject("Layout do site gerado", "project.generator.layout"))

  private def js = Seq(
    new GithubProject("Setup do Jasmine", "js.jasmine.setup"),
    new GithubProject("Matchers do Jasmine", "js.jasmine.matchers"),
    new GithubProject("Declarar Funções", "js.declarar.funcoes"),
    new GithubProject("If", "js.if"),
    new GithubProject("`For`...in", "js.for.in"),
    new GithubProject("Funções construtoras", "js.constructor.function"),
    new GithubProject("Hashes", "js.hash"),
    new GithubProject("`For` em um objeto", "js.for.object"),
    new GithubProject("`For` em um hash", "js.for.hash"),
    new GithubProject("Filter", "js.filter"),
    new GithubProject("Map", "js.map"),
    new GithubProject("Reduce", "js.reduce"),
    new GithubProject("Criar Tags", "js.criar.tags"),
    new GithubProject("Inserir Tags", "js.inserir.tags"),
    new GithubProject("Remover Tags", "js.remover.tags"),
    new GithubProject("Criar Tags com JQuery", "js.criar.tags"),
    new GithubProject("Inserir Tags com JQuery", "js.inserir.tags"),
    new GithubProject("Remover Tags com JQuery", "js.remover.tags"))

  private def codeReview = Seq(
    new GithubProject("Code Review", "project.review.setup"),
    new GithubProject("Tela que recebe diretório", "project.review.dir"),
    new GithubProject("Exibir os arquivos", "project.review.show.files"),
    new GithubProject("Syntax Highlighting", "project.review.highlight"),
    new GithubProject("Anotações", "project.review.annotation"),
    new GithubProject("Exibir o resultado do code review", "project.review.result"))

  def description = "Aprenda a construir aplicações web usando os frameworks que quiser"

  override def hasTutor = true

  override def name = "Desenvolvimento Web"

}

