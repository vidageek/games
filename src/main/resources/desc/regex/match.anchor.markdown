
Âncoras servem para dar uma referência para as suas RegEx. Por exemplo, você pode querer reconhecer `tomate`,
mas não querer reconhecer `o tomate`.

Para fazer isso, você pode usar um operador como o `^`, que âncora ao começo da linha, o `$` que 
âncora ao fim da linha e o `\b`, que ancora a qualquer coisa que não seja um caractere ou número.

Exemplo:
* A RegEx `^tomate` <strong>reconhece</strong> o padrão `tomate`, mas não reconhece `o tomate`
* A RegEx `tomate$` <strong>reconhece</strong> o padrão `tomate` e `bom tomate`, mas não reconhece `tomates`
* A RegEx `\btomate\b` <strong>reconhece</strong> o padrão `tomate` e `bom tomate` e `tomate gostoso`, mas não reconhece `tomates`
