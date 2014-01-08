Existem alguns casos em que queremos, dentro de uma RegEx, reconhecer um trecho previamente capturado.

Um exemplo clássico é encontrar o conteúdo entre duas tags

Para isso, podemos usar **back references**, ou seja, referências à algo previamente capturado.

Para utilizar uma **back reference**, utilizamos o caractere `\` seguido pelo número
do grupo de captura que queremos referenciar. Vale lembrar que o grupo `0` é tudo que a RegEx encontrou. 
Então só faz sentido começarmos a referenciar a partir do `1`.

Exemplo:
* A RegEx `(a)\1` **reconhece** o padrão `aa`
* A RegEx `<([^>]+)>[^<]+</\1>` **reconhece** o padrão `<b>abc</b>`, mas **não reconhece** o padrão `<b>abc</i>`
* A RegEx `(b+)\1` **reconhece** todas as sequências de `b` que tenham um número de caracteres pares (como `bb`, `bbbb`, etc)
