Existem dois atributos que afetam quantas linhas ou colunas o `<th>` ou o `<td>` vão ocupar:

* `rowspan="N"` indica que o `<td>` ou `<th>` irá ocupar **N** linhas
* `colspan="N"` indica que o `<td>` ou `<th>` irá ocupar **N** colunas 

Isso significa que se você escrever o seguinte código:

    <table>
        <tr><td colspan="2" rowspan="2">Super célula</td><td>normal 1</td></tr>
        <tr><td>normal 2</td></tr>
        <tr><td>normal 3</td><td>normal 4</td><td>normal 5</td></tr>
    </table>

Ele será renderizado com a primeira célula ocupando o espaço de outras 3 células:

<table>
<tr><td colspan="2" rowspan="2">Super célula</td><td>normal 1</td></tr>
<tr><td>normal 2</td></tr>
<tr><td>normal 3</td><td>normal 4</td><td>normal 5</td></tr>
</table>
