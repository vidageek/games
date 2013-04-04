# Preparando o Ambiente


* Certifique-se de ter uma versão do Eclipse instalada no seu computador
* Instale uma versão recente do [Scala IDE para Scala 2.10][1] seguindo os passos abaixo


1) No Eclipse, acesse o menu "Help" -> "Install New Software".
2) Na caixa "Work With:", selecione a opção "All Available Sites", e na caixa de busca insira a url do Scala IDE. Marque a primeira opção e pressione "Next".
3) O assistente de instalação exibirá os itens escolhidos no passo anterior. Prossiga com o botão "Next".
4) Leia o contrato de licença, e caso concorde, marque a opção e clique no botão "Finish" para concluir o assistente. Você deverá reiniciar o Eclipse para que as alterações entrem em vigor.

* Instale o [sbt 0.12.2][2]. Basta efetuar o download do pacote para seu sistema operacional e extrair os arquivos (alguns já possuem um assistente de instalação).
* [Crie um fork][3] do projeto git@github.com:vidageek/games.git
* execute `sbt eclipse` dentro da pasta do projeto
* importe o projeto no eclipse
* Acrescente `-language:_` em Propriedades do Projeto > Scala Compiler > Additional command line parameters. Isso desabilita
os feature warnings de Scala (Não vamos restringir o uso de nenhuma feature).


[1]: http://scala-ide.org/download/milestone.html#scala_ide_21_milestone_3
[2]: http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html
[3]: https://help.github.com/articles/fork-a-repo