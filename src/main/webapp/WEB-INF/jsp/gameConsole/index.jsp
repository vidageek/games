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
			</div>
		</c:if>  
		<h1>${game.name}</h1>
		<h2>${game.description}</h2>
		Voc&ecirc; pode come&ccedil;ar a jogar pelo <a href="/play/${gameName}/task/0">primeiro exerc&iacute;cio</a> ou escolher um grupo abaixo:
		
		<ul class="nav nav-pills nav-stacked">
			<c:set var="a" value="" />
			<c:forEach items="${game.tasks}" var="task">
					<c:if test="${a != task.groupName}">
						<c:set var="a" value="${task.groupName}" />
						<li><a href="/play/${gameName}/task/${task.index}">${task.groupName}</a></li>
					</c:if>
			</c:forEach>
		</ul>
	</body>
</html>
