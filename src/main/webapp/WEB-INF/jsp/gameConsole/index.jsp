<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Comece a jogar o ${game.name} Game</title>
	</head>
	<body>
		<div class="alert">
		  <strong>Aviso!</strong> Estamos em Beta. Caso encontre algum problema, envie um email para games@vidageek.net
		</div>
		<c:if test="${not empty gameEnded}">
			<div class="alert alert-success">
				Parabéns! Você acabou de resolver o último exercício de ${game.name}. O importante agora é continuar praticando. <br />
				
				Compartilhe a sua conquista com seus amigos no 
				<a target="_blank" href="http://www.facebook.com/sharer.php?u=http://games.vidageek.net/play/${gameName}">Facebook</a>
				 e 
				<a target="_blank" href="http://twitter.com/share?text=Acabei%20de%20terminar%20o%20${game.name}%20Game.%20Quer%20aprender%20${game.name}%20tamb%C3%A9m%3F&url=http://games.vidageek.net/play/${gameName}">Twitter</a>
			</div>
		</c:if>  
		<h1>${game.name}</h1>
		<h2>${game.description}</h2>
		Voc&ecirc; pode come&ccedil;ar a jogar pelo <a href="/play/${gameName}/task/0">primeiro exerc&iacute;cio</a> ou escolher um grupo abaixo:
		
		<ul class="nav nav-pills nav-stacked groups">
			<c:set var="a" value="" />
			<c:forEach items="${game.tasks}" var="task">
					<c:if test="${a != task.groupName}">
						<c:set var="a" value="${task.groupName}" />
						<c:set var="keyName" value="${gameName}.${task.groupCode}" />
						<li class="${finishedGroups[keyName]}"><a href="/play/${gameName}/task/${task.index}">${task.groupName}</a></li>
					</c:if>
			</c:forEach>
		</ul>
		<h3>Outros recursos</h3>
		<ul class="nav nav-pills nav-stacked groups">
			<li><a href="/reference/${gameName}">Referência</a>
		</ul>
	</body>
</html>
