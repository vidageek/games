A parte mais básica para a construção de uma Expressão Regular (RegEx)
são os caracteres simples. As RegEx mais básicas são compostas 
apenas por caracteres simples, como `a`, `1` e `C`.

Exemplo:
* A RegEx `z` **reconhece** o padrão `z`
* A RegEx `abcde` **reconhece** o padrão `abcde`
* A RegEx `q` **não** reconhece o padrão `m`

A exceção à essa regra são alguns caracteres, que possuem 
um **significado especial** dentro das RegEx 
(`$, ^, *, (, ), +, {, }, [, ], \, |, ?, -, .`). Para utilizá-los 
como caracteres normais, você precisa colocar uma **`\`** antes deles.

Além desses, existem ainda alguns caracteres que não são visíveis, e dentro de RegEx eles são representados usando
caracteres escapados:
* O `tab` é representado por `-Tab-`, mas na RegEx você deve usar `\t`
* A `quebra de linha` é representada por `-Quebra-de-Linha-`, mas na RegEx você deve usar `\n`
* A `quebra de página` é representada por `-Quebra-de-Pagina-`, mas na RegEx você deve usar `\f`
* O `retorno` é representada por `-Retorno-`, mas na RegEx você deve usar `\r`
* O `tab vertical` é representada por `-Tab-Vertical-`, mas na RegEx você deve usar `\x0B`
* O `espaço em branco` é representado por `-Espaço-`, mas você continua apenas apertando a barra de espaço :)
* O `padrão vazio` é representado por `-Vazio-`, mas você simplesmente não escreve nada :)

Exemplo:
* A RegEx `$` **não** reconhece o padrão `$`
* A RegEx `\$` **reconhece** o padrão `$`
* A RegEx `(` **não** reconhece o padrão `(` e é inválida.
* A RegEx `ac\$\{6` **reconhece** o padrão `ac${6`

O último detalhe que é importante levar em consideração é que as RegEx diferenciam 
caracteres maiúsculos de caracteres minúsculos. 
