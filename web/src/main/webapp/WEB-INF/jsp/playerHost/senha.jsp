<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>VidaGeek Games - Porquê você não precisa colocar a sua senha?</title>
		<meta name="robots" content="noindex" />
	</head>
	<body>
		<header>
			<h1>Porquê você não precisa colocar a sua senha?</h1>
		</header>
		<div class="row ">
			<div class="span8 offset2 well">
				<p>
				Uma das razões para você não precisar de uma senha é simplesmente a facilidade de uso. É uma coisa a 
				menos para te atrapalhar no momento em que você quiser jogar. 
				</p><p>
				Mas não é apenas isso. Não é uma boa troca deixar segurança de lado para aumentar a facilidade.
				</p><p>
				A principal razão que nos motivou a fazer dessa forma é que o modelo de usuário e senha não funciona.
				</p><p>
				Para uma senha ser segura, é necessário que ele seja o mais única possível e difícil de ser adivinhada.
				</p><p>
				O problema é que senhas assim são muito difíceis de serem lembradas. E o que fazemos quando não conseguimos
				lembrar de algo que precisamos? Anotamos em um <a target="_blank" href="https://www.google.com/search?tbm=isch&q=post+it+password">post-it</a>
				embaixo do teclado. Ou na parede. Enfim, em um lugar que outras pessoas vão ver.
				</p><p>
				Ou seja, a senha passa a ser apenas um incômodo, sem acrescentar segurança real.
				</p><p>
				Para resolver esse problema, o que fizemos:
				<ul>
					<li>Quando você entra no site pela primeira vez, clica em Login e coloca o seu email, nós criamos uma senha super-segura pra você 
					(com 120 caracteres). Essa senha é completamente inviável de ser copiada para um post it. Também não dá para lembrar.</li>
					<li>Como você não consegue se lembrar dela, não vamos pedir para você fazer isso. Só vamos pedir para você clicar em um link no seu
					email. Esse link vai enviar para nós a sua senha super segura.</li>
					<li>Quando nós recebermos a sua senha super segura, vamos logar você e deixar você logado até que você clique em Logout. Se você
					estiver no seu computador de casa, poderá ficar logado para sempre. Se estiver em um computador compartilhado, você precisará
					clicar em Logout antes de sair (do mesmo modo que com senha)</li>
					<li>Se algum dia você perder o email com o link de login, é só colocar o seu email novamente que enviamos o link para você.</li>
				</ul>
				</p>
			</div>
		</div>
	</body>
</html>