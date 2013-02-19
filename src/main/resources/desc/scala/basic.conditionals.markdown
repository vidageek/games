
Usamos estruturas condicionais quando queremos executar código apenas em determinados momentos. Se uma condição é verdadeira, executamos uma parte do código, se essa condição é falsa mas há outra verdadeira, executamos algum outro código, senão executamos um terceiro.

Imagine que rolamos um dado, se sair 1 ou 2 vamos imprimir `"perdeu"`, se sair 3 ou 4 imprimimos `"empatou"` e no caso de 5 ou 6 imprimimos `"ganhou"`

<pre>if(dado == 1 || dado == 2) {
  println("perdeu")
} else if(dado == 3 || dado == 4) {
  println("empatou")
} else {
  println("ganhou")
}</pre>

A condição deve ser sempre um valor booleano. O if é uma expressão em Scala, então podemos fazê-lo retornar valores. Exemplo:

* `if(3 > 2) { "maior" }` vai devolver "maior"
* `if(3 > 2) { 123 }` vai devolver 123
* `if(3 < 2) { "menor" }` não vai devolver nada, já que não satisfaz a condição.
