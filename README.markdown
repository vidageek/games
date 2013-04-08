# VidaGeek Games
## Uma forma mais simples de aprender


# Como preparar o Ambiente

* Instale o [sbt 0.12.2][1]
* Clone o projeto [git@github.com:vidageek/games.git][2]
* Se for utilizar o Eclipse:
  * Instale uma versão recente do [Scala IDE para Scala 2.10][3]
  * Execute `sbt eclipse` dentro da pasta do projeto
  * Importe o projeto no Eclipse
  * Acrescente `-language:_` em Propriedades do Projeto > Scala Compiler > Additional command line parameters. Isso desabilita
os feature warnings de Scala (Não vamos restringir o uso de nenhuma feature).
* Se for utilizar o NetBeans:
  * Instale o plugin [nbscala][4]
  * Instale o plugin [nbsbt][5] (que também é mencionado na documentação do *nbscala*)
  * Abra o projeto no NetBeans
  * [Aqui tem um video ilustrando esses passos][6]


# Para Executar Localmente
## Rodar SBT
    Basta entrar pelo console no diretório do projeto:
    $ sbt
    $ > gzip-css
    $ > gzip-js
    $ > container:start


[1]: http://scala-sbt.org/release/docs/Getting-Started/Setup.html
[2]: git@github.com:vidageek/games.git
[3]: http://scala-ide.org/download/current.html
[4]: https://github.com/dcaoyuan/nbscala
[5]: https://github.com/dcaoyuan/nbsbt
[6]: https://www.youtube.com/watch?v=aDKBF9H9rSY
