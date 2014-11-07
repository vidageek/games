# VidaGeek Games
## Uma forma mais simples de aprender

[![Build Status](https://travis-ci.org/vidageek/games.svg?branch=master)](https://travis-ci.org/vidageek/games)

# Licenças

O código fonte de nossa autoria (arquivos `.scala`, `.sh`, `.sbt` e outros) estão 
licenciados sob [GPLv3][7], exceto o código contido na pasta `games/game`, que é 
licenciado sob [MIT][9].

O conteúdo das explicações dos exercícios (encontrado em `src/main/resources/desc` de cada jogo) 
está licenciado sob [CC-BY][8].


# Como preparar o Ambiente

* Instale o [sbt][1]
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
    $ > container:start

# Como criar um jogo

1. Caso não esteja familiarizado com o código, dê uma olhada em como o [RegexGame][12] é implementado
2. Declare o seu jogo no [arquivo de build][13] `lazy val ${path}Game = gameProject("${PATH}")` , 
sendo que ${PATH} é o que determina a url onde o jogo fica (por exemplo, em `/play/regex`, 
o ${PATH} é `regex`)
3. Coloque o seu projeto como dependência do projeto `web` e como agregado do projeto `root`
3. No `sbt`, rode `eclipse with-sources=true` para que o sbt prepare o seu projeto.
4. Crie uma classe que estenda a trait [GameEngine][10]. O nome completo dessa classe deve ser
`vggames.${PATH}.${PATH_COM_PRIMEIRA_LETRA_MAIUSCULA}Game`.
5. Crie uma classe que estenda a trait [GameView][11]. O nome dessa classe deve ser o nome da
classe criada no passo anterior seguida por `View`.


[1]: http://scala-sbt.org/release/docs/Getting-Started/Setup.html
[2]: git@github.com:vidageek/games.git
[3]: http://scala-ide.org/download/current.html
[4]: https://github.com/dcaoyuan/nbscala
[5]: https://github.com/dcaoyuan/nbsbt
[6]: https://www.youtube.com/watch?v=aDKBF9H9rSY
[7]: https://github.com/vidageek/games/blob/master/LICENSE.GPL
[8]: https://creativecommons.org/licenses/by/3.0/deed.pt_BR
[9]: https://github.com/vidageek/games/blob/master/LICENSE.MIT
[10]: https://github.com/vidageek/games/blob/master/games/game/src/main/scala/vggames/shared/GameEngine.scala
[11]: https://github.com/vidageek/games/blob/master/games/game/src/main/scala/vggames/shared/GameView.scala
[12]: https://github.com/vidageek/games/blob/master/games/regex/src/main/scala/vggames/regex/RegexGame.scala
[13]: https://github.com/vidageek/games/blob/master/project/GamesVidageekBuild.scala
