<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>VidaGeek Games</title>
	</head>
	<body>
		<header>
			<h1>VidaGeek Games</h1>
		</header>
		<div class="row why span8 offset2">
				<p> O VidaGeek Games é uma iniciativa <strong>OpenSource</strong> para criar material de aprendizado atravéz da prática.
				Para isso criamos <strong>jogos</strong> nos quais você <strong>aprende</strong> conforme avança.</p>
				
				<p>Ainda existe muito trabalho a ser feito, mas você já pode sentir o gostinho com alguns dos jogos abaixo:</p>
				
				<ul>
				<c:forEach var="game" items="${cfg.activeGames}">
					<c:set var="g" value="${games[game]}" />
					<li><a href="/play/${game}">${g.name} Game</a>: <p>${g.description}</p></li>
				</c:forEach>
				</ul>
				
				<p>E também estamos trabalhando em jogos sobre:</p>
				
				<ul>
				<c:forEach var="game" items="${cfg.inactiveGames}">
					<c:set var="g" value="${games[game]}" />
					<li>${g.name}</li>
				</c:forEach>
				</ul>
		</div>
	</body>
</html>