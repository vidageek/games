<p>
Opera&ccedil;&otilde;es comuns de <code>List</code>:
</p>
<ul>
<li>Voc&ecirc; pode "criar" <code>Listas</code> com inteiros: <code>List(4,5,6)</code> vai gerar a lista com os valores <code>"4,5,6"</code>.</li>
<li>Voc&ecirc; pode "criar" <code>Listas</code> com strings: <code>List("banana","morango","uva")</code> vai gerar a lista com as strings <code>"banana,morango,uva"</code>.</li>
</ul>

Podemos usar o método <code>filter</code> de um <code>List</code> para retornar somente os elementos usando alguma condição. 
Por exemplo, dada um List <code>lista</code> com os valores <code>1,3,4,10,3,10</code> o código abaixo retorna uma lista contendo 
valores diferentes de 10:

<pre>
lista.filter(_ != 10)
</pre>
  
No exemplo acima, <code>"_"</code> indica que são todos os elementos da lista.
