
Uma das primeiras coisas que costumamos aprender quando começamos
a programar é mudar o valor de variáveis. Embora pareça muito
prático fazer isso, mudar valores trazem alguns problemas ao
design do nosso código:

- Você não tem certeza (sem ler o código todo) se a variável/atributo
possui o valor que você usou para inicializar ela.
- É difícil acompanhar as mudanças de valores, em especial quando
você passa um objeto para outros métodos ou funções.
- O código não funciona em um ambiente [concorrente][1] (e aplicações web
costumam ser ambientes concorrentes)

Para evitar esses problemas, a solução é não mudar os valores de nada (exceto
em lugares onde mudar o valor vale os riscos que você corre). Para isso, 
basta seguir as seguintes orientações:

- Atributos só podem ser inicializados diretamente ou no construtor
- Atributos devem ser imutáveis (ou seja, respeitar essas mesmas orientações)
- Nenhum método ou função pode fazer atribuição aos atributos
- Em linguagens de programação que possuem threads reais (como Java e C#),
você deve marcar os atributos como somente leitura (`final` em Java e `readonly`
em C#)
- Quando precisar mudar o valor de um atributo, você deve criar um novo objeto
com esse valor novo e devolver ele para quem chamou o método ou função

Agora pegue o seu projeto da tartaruga e faça com que o código todo dele seja imutável.

De agora em diante, todo o código que você escrever deve ser imutável.


[1]: https://pt.wikipedia.org/wiki/Programa%C3%A7%C3%A3o_concorrente
