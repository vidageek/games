package vggames.html

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class HtmlGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(
    basicStructures,
    basicStructures2,
    basicStructures3,
    formats1,
    formats2,
    links,
    structures,
    images,
    multimedia,
    unorderedLists,
    orderedlists,
    definitionlists,
    tables,
    tables2,
    forms1,
    forms2,
    forms3,
    forms4,
    //editable,
    divs,
    svgs //datalists
    )

  private def basicStructures = new TaskGroup("Exercícios básicos I", "html.basic", descriptions,
    new HtmlTask("Crie um título (h1) com o texto 'título'", "basic_h1"),
    new HtmlTask("Crie um título (h1) com o texto 'Outro Título' e quebra de linha entre as palavras", "basic_br"));

  private def basicStructures2 = new TaskGroup("Exercícios básicos II", "html.basic2", descriptions,
    new HtmlTask("Crie um parágrafo com \"Oi mundo\" como conteúdo", "basic_p1"));

  private def basicStructures3 = new TaskGroup("Exercícios básicos III", "html.basic3", descriptions,
    new HtmlTask("Crie um parágrafo com o texto: 'Hello world!'", "basic"),
    new HtmlTask("Crie um sub-título (h2) com o texto 'título 2'", "basic_h2"),
    new HtmlTask("Crie um sub-título (h3) com o texto 'título 3'", "basic_h3"));

  private def images = new TaskGroup("Imagens", "html.images", descriptions,
    new HtmlTask("Inclua uma imagem do endereço 'http://www.vidageek.net/images/logo.png' alt='logo'", "image_1"),
    new HtmlTask("Inclua uma imagem do endereço 'http://www.vidageek.net/images/logo.png' " +
      "com uma altura de 100 pixels e 'logo' como texto alternativo", "image_2"),
    new HtmlTask("Inclua uma imagem do endereço 'http://www.vidageek.net/images/logo.png' " +
      "com altura de 100 pixels, largura 200 pixels e 'logo' como texto alternativo", "image_3"),
    new HtmlTask("Inclua uma imagem do endereço 'http://www.vidageek.net/images/logo.png' " +
      "com a legenda 'Logo do VidaGeek Games'.", "image_4"));

  private def multimedia = new TaskGroup("Multimídia", "html.multimedia", descriptions,
    new HtmlTask("Insira o vídeo com o seguinte endereço: <br/><b>\"http://www.html5rocks.com/en/tutorials/video/basics/Chrome_ImF.ogv\"</b><br/> com o tipo mp4.", "multi_1"),
    new HtmlTask("Insira o audio com o seguinte endereço: <br/><b>\"http://www.w3schools.com/html/horse.ogg\"</b><br/> com o tipo ogg.", "multi_2"));

  private def unorderedLists = new TaskGroup("Listas não ordenadas", "html.unorderedlists", descriptions,
    new HtmlTask("Crie uma lista não ordenada com os elementos: Carro, Moto e Barco", "list_1"))

  private def orderedlists = new TaskGroup("Listas ordenadas", "html.orderedlists", descriptions,
    new HtmlTask("Crie uma lista ordenada com os elementos: Abacaxi, Uva e Banana", "list_2"))

  private def definitionlists = new TaskGroup("Listas de definições", "html.definitionlists", descriptions,
    new HtmlTask("Crie uma lista de definições com os elementos Grama e Sol e suas respectivas cores Verde e Amarelo", "list_3"));

  private def links = new TaskGroup("Links", "html.links", descriptions,
    new HtmlTask("Crie um link para \"www.google.com\"", "link_1"),
    new HtmlTask("Crie um título usando (h1) chamado \"Home\" que seja também um link para a página \"http://home.com\"", "link_2"),
    new HtmlTask("Crie um link \"referencia\" que aponte para o marcador \"indice\"", "link_3"));

  private def formats1 = new TaskGroup("Formatação de texto I", "html.formats", descriptions,
    new HtmlTask("Crie um parágrafo com o texto \"texto em itálico\" com a palavra \"itálico\" em itálico", "format_1"),
    new HtmlTask("Crie um parágrafo com o texto \"texto grande\" com a palavra \"grande\" usango a tag big", "format_2"),
    new HtmlTask("Crie um parágrafo com o texto \"texto pequeno\" com a palavra \"pequeno\" usando a tag small", "format_3"));

  private def formats2 = new TaskGroup("Formatação de texto II", "html.formats2", descriptions,
    new HtmlTask("Crie um parágrafo com o texto no formato mostrado no exemplo da direita", "format_4"),
    new HtmlTask("Escreva o código \"x=42+y;\" usando a tag code", "format_5"),
    new HtmlTask("Escreva o texto mostrado no exemplo da direita usando a tag pre", "format_6"));

  private def tables = new TaskGroup("Tabelas", "html.tables", descriptions,
    new HtmlTask("Crie uma tabela com borda, de 2 linhas e 2 colunas, com os números de 1 a 4", "table_1"));

  private def tables2 = new TaskGroup("Tabelas II", "html.tables2", descriptions,
    new HtmlTask("Crie uma tabela de 2 linhas e 2 colunas, mesclando as duas primeiras células e com o conteúdo em negrito conforme o exemplo", "table_2"),
    new HtmlTask("Crie uma tabela de 2 linhas e 2 colunas, mesclando a primeira coluna e com o conteúdo em negrito conforme o exemplo", "table_3"));

  private def divs = new TaskGroup("Divs", "html.divs", descriptions,
    new HtmlTask("Crie uma div com um bloque spam com o texto 'Minha querida div'", "div_1"));

  private def forms1 = new TaskGroup("Formulários I", "html.forms1", descriptions,
    new HtmlTask("Crie um formulário que envia os resultados para \"resultado.php\" usando o método \"get\"", "form_1"));

  private def forms2 = new TaskGroup("Formulários II - Controles", "html.forms2", descriptions,
    new HtmlTask("Crie um formulário com o texto \"Texto: \" seguido por uma caixa de texto", "form_2"),
    new HtmlTask("Crie um formulário com o texto \"Senha: \" seguido por uma caixa de senha", "form_3"),
    new HtmlTask("Crie um formulário com o texto \"Texto: \" seguido por uma caixa de texto com o conteúdo \"Texto!\"", "form_4"));

  private def forms3 = new TaskGroup("Formulários III - Controles", "html.forms3", descriptions,
    new HtmlTask("Crie um formulário com dois checkbox, o primeiro seguido por \"Entrada 1\" e em outra linha um segundo checkbox seguido por \"Entrada 2\"", "form_5"),
    new HtmlTask("Crie um formulário com dois radio-buttons, o primeiro seguido pelo texto \"Entrada 1\" e em outra linha um segundo radio-button seguido por \"Entrada 2\". Ambos pertencendo ao grupo \"grupo\"", "form_6"));

  private def forms4 = new TaskGroup("Formulários IV - Controles", "html.forms4", descriptions,
    new HtmlTask("Crie um formulário com um botão com o texto \"Botão\"", "form_7"),
    new HtmlTask("Crie um formulário com um botão de submissão com o texto \"Enviar\"", "form_8"),
    new HtmlTask("Crie um formulário com uma caixa de texto antecedida pelo texto \"Usuário:\", na linha seguinte, uma caixa de senha antecedida pelo texto \"Senha:\". Na seguinte linha dois botões: um com o texto \"Login\" e outro com o texto \"Cancelar\"", "form_9"));

  private def editable = new TaskGroup("Editable", "html.editable", descriptions,
    new HtmlTask("Crie um parágrafo editável com o conteúdo \"O Brasil foi descoberto no ano 1500.\"", "editable_1"));

  private def structures = new TaskGroup("Estruturas do arquivo", "html.structures", descriptions,
    new HtmlTask("Escreva toda a estrutura de um arquivo html com o título \"Página html\" e conteúdo \"Oi mundo\"", "structure_1"),
    new HtmlTask("Escreva a estrutura de um arquivo html com o título \"Minha página\" contendo o texto \"Conteúdo da minha página\" em negrito", "structure_2"),
    new HtmlTask("Escreva a estrutura de um arquivo html com o título \"Página com link\" contendo um link para a página do Google \"www.google.com\" com o texto \"Google\"", "structure_3"),
    new HtmlTask("Declare um documento HTML5 usando a tag DOCTYPE, conforme o exemplo na descrição", "structure_4"));

  private def svgs = new TaskGroup("SVG", "html.svg", descriptions,
    new HtmlTask("Desenhe um círculo com interior azul, na posição (50,50) e raio 50", "svg_1"),
    new HtmlTask("Desenhe um retângulo cinza na posição (50,50) de largura 40 e altura 20", "svg_2"),
    new HtmlTask("Desenhe um círculo com interior branco, na posição (40,40), raio 10 e contorno preto de 3 pixels", "svg_3"));

  private def datalists = new TaskGroup("Datalist", "html.datalist", descriptions,
    new HtmlTask("Crie um campo input com um datalist da lista chamada transporte, e tendo as opções Carro, Moto e Barco", "datalist_1"));

  def getDescription = "Um jogo muito legal para aprender Html, focado em Html5"

  def getName = "Html"

}
