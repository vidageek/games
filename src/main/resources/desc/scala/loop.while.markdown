Um while loop é uma estrutura de repetição. Ele pode ser utilizado quando
se precisa realizar alguma ação muitas vezes. Costuma ser usado em conjunto
com arrays.
 
A forma geral do while é

<pre>while ( EXPRESSAO BOOLEANA ) {
  ACAO1
  ACAO2
  ...
}</pre>

No lugar de `EXPRESSAO BOOLEANA` se insere uma expressão Scala que avalia
para um valor do tipo `Boolean`. Um exemplo é uma comparação de valores, como
`i < tamanho_maximo` ou `i == 0`. Essa expressão será avaliada a cada execução
do loop. O loop para de executar quando essa expressão avalia para `false`.

As linhas de `ACAO` são expressões que serão executadas repetidamente. Geralmente
ao menos uma das ações modifica o valor de uma `var` que consta na `EXPRESSAO 
BOOLEANA`, assim a `EXPRESSAO BOOLEANA` eventualmente se torna `false` e o loop
para de executar. Loops que nunca param de rodar normalmente indicam um problema
no seu código, chamado de _loop infinito_.