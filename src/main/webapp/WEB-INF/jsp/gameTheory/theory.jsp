<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Teoria do ${game.name} Game</title>
	</head>
	<body>
		<h1>Teoria do ${game.name} Game</h1>
		
		<a id="conteudo" class="theory-link"></a>
		<h2 class="theory">Conte&uacute;do:</h2>
		<ul class="nav nav-pills nav-stacked groups">
			<c:set var="a" value="" />
			<c:forEach items="${game.tasks}" var="task">
					<c:if test="${a != task.groupName}">
						<c:set var="a" value="${task.groupName}" />
						<c:set var="keyName" value="${gameName}.${task.groupCode}" />
						<li class="${finishedGroups[keyName]}"><a href="#${task.groupCode}">${task.groupName}</a></li>
					</c:if>
			</c:forEach>
		</ul>
		
		<c:set var="a" value="" />
		<c:forEach items="${game.tasks}" var="task">
				<c:if test="${a != task.groupName}">
					<c:set var="a" value="${task.groupName}" />
					<c:set var="keyName" value="${gameName}.${task.groupCode}" />
					<a id="${task.groupCode}" class="theory-link"></a>
					<div>
						<h1><a href="#${task.groupCode}">${task.groupName}</a></h2>
						${task.description}
						<a class="btn" href="#conteudo">Topo</a>
						<a class="btn btn-info" href="/play/${gameName}/task/${task.index}">Jogar!</a>
					</div>
				</c:if>
		</c:forEach>
		
	</body>
</html>
