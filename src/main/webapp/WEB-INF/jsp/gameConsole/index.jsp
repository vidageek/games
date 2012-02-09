<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
	</head>
	<body>
		<h1>${game.name}</h1>
		<h2>${game.description}</h2>
		Voc&ecirc; pode come&ccedil;ar a jogar pelo <a href="/play/${gameName}/task/0">primeiro exerc&iacute;cio</a> ou escolher um abaixo:
		
		<ul>
		<c:forEach items="${game.tasks}" var="task">
			<li><a href="/play/${gameName}/task/${task.index}">${task}</a></li>
		</c:forEach>
	</body>
</html>
