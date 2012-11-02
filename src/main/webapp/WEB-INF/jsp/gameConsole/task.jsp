<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Exerc&iacute;cio ${task.index} do ${game.name} game</title>
		<meta name="robots" content="noindex" /> 
	</head>
	<body>
		<c:import url="/WEB-INF/fragments/task_${gameName}.jsp" />
	</body>
</html>