Html também suporta a exibição de dados em forma de tabelas. Para isso utilizamos as seguintes tags:

* `<table>` marca o começo de uma tabela
* `<thead>` (opcional) marca o cabeçalho da tabela
* `<tbody>` (opcional) marca o corpo da tabela
* `<tfooter>` (opcional) marca o rodapé da tabela
* `<tr>` marca as linhas da tabela
* `<th>` (opcional) marca os títulos da coluna
* `<td>` marca as células da tabela

Várias tags, mas no fim das contas, as tabelas costumam ter a seguinte cara:

    <table>
        <thead>
            <tr><th>Coluna 1</th><th>Coluna 2</th>
        </thead>
        <tbody>
            <tr><td>Valor 1 da Coluna 1</td><td>Valor 1 da Coluna 2</td></tr>
            <tr><td>Valor 2 da Coluna 1</td><td>Valor 2 da Coluna 2</td></tr>
        </tbody>
        <tfoot>
            <tr><th>Coluna 1</th><th>Coluna 2</th>
        </tfoot>
    </table>

E isso seria renderizado como


<table>
<thead>
<tr><th>Coluna 1</th><th>Coluna 2</th>
</thead>
<tbody>
<tr><td>Valor 1 da Coluna 1</td><td>Valor 1 da Coluna 2</td></tr>
<tr><td>Valor 2 da Coluna 1</td><td>Valor 2 da Coluna 2</td></tr>
</tbody>
<tfoot>
<tr><th>Coluna 1</th><th>Coluna 2</th>
</tfoot>
</table>
