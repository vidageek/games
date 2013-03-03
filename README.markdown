# VidaGeek Games
## Uma forma mais simples de aprender

# Para Executar Local
## Rodar SBT
    Basta entrar pelo console no diretório do projeto:
    $ sbt
    $ > gzip-css
    $ > gzip-js
    $ > container:start

# Como preparar o Ambiente

* Instale uma versão recente do [Scala IDE para Scala 2.10][1]
* Instale o [sbt 0.12.2][2]
* clone o projeto git@github.com:vidageek/games.git
* execute `sbt eclipse` dentro da pasta do projeto
* importe o projeto no eclipse
* Acrescente `-language:_` em Propriedades do Projeto > Scala Compiler > Additional command line parameters. Isso desabilita
os feature warnings de Scala (Não vamos restringir o uso de nenhuma feature).


[1]: http://scala-ide.org/download/milestone.html#scala_ide_21_milestone_3
[2]: http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html
