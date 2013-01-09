<p>
Um recurso muito poderoso de classes de caracteres &eacute; a possibilidade de definir classes 
que n&atilde;o contenham determinados caracteres.
</p>
<p>
Para fazer isso, voc&ecirc; acrescenta um <code>^</code> logo ap&oacute;s o <code>[</code> que marca o 
come&ccedil;o da classe de caracteres.
</p>

Exemplo:
<ul>
	<li>A RegEx <code>[^a]</code> reconhece qualquer caractere, exceto <code>a</code>.</li>
	<li>A RegEx <code>[^b-d]</code> reconhece qualquer caractere, exceto <code>b</code>, <code>c</code> e <code>d</code>.</li>
	<li>A RegEx <code>[^\d]</code> reconhece qualquer caractere, exceto <code>0</code>, <code>1</code>, <code>2</code>, <code>3</code>, <code>4</code>, <code>5</code>, <code>6</code>, <code>7</code>, <code>8</code> e <code>9</code>.</li>   
</ul>

<p>
Tamb&eacute;m existem classes de caracteres opostas pr&eacute;-definidas:
<ul>
	<li>A classe <code>\D</code> &eacute; o oposto de <code>\d</code>, ou seja, equivalente &agrave; <code>[^0-9]</code></li>
	<li>A class <code>\S</code> &eacute; o oposto de <code>\s</code>, ou seja, equivalente &agrave; <code>[^ \t\n\x0B\f\r]</code></li>
	<li>A classe <code>\W</code> &eacute; o oposto de <code>\w</code>, ou seja, equivalente &agrave; <code>[^a-zA-Z_0-9]</code></li>
</ul>
</p>

Exemplo:
<ul>
	<li>A RegEx <code>\D</code> reconhece qualquer caractere que n&atilde;o seja <code>0</code>, <code>1</code>, <code>2</code>, <code>3</code>, <code>4</code>, <code>5</code>, <code>6</code>, <code>7</code>, <code>8</code> e <code>9</code>.</li>
</ul>	
	