<p>Existem alguns casos em que queremos, dentro de uma RegEx, reconhecer um trecho previamente capturado.</p>

<p>Um exemplo cl&aacute;ssico &eacute; encontrar o conte&uacute;do entre duas tags</p>

<p>Para isso, podemos usar <strong>back references</strong>, ou seja, refer&ecirc;ncias &agrave; algo previamente capturado.</p>

<p>Para utilizar uma <strong>back reference</strong>, utilizamos o caractere <code>\</code> seguido pelo n&uacute;mero
do grupo de captura que queremos referenciar. Vale lembrar que o grupo <code>0</code> &eacute; tudo que a RegEx encontrou. 
Ent&atilde;o s&oacute; faz sentido come&ccedil;armos a referenciar a partir do <code>1</code>.</p>

Exemplo:
<ul>
	<li>A RegEx <code>(a)\1</code> <strong>reconhece</strong> o padr&atilde;o <code>aa</code></li>
	<li>A RegEx <code>&lt;([^&gt;]+)&gt;[^&lt;]+&lt;/\1&gt;</code> <strong>reconhece</strong> o padr&atilde;o 
		<code>&lt;b&gt;abc&lt;/b&gt;</code>, mas <strong>n&atilde;o reconhece</strong> o padr&atilde;o <code>&lt;b&gt;abc&lt;/i&gt;</code></li>
	<li>A RegEx <code>(b+)\1</code> <strong>reconhece</strong> todas as sequ&ecirc;ncias de <code>b</code> que tenham um 
	n&uacute;mero de caracteres pares (como <code>bb</code>, <code>bbbb</code>, etc)</li>
</ul>