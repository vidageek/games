<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty judgedTask}">
	${judgedTask.reason}
</c:if>  

${task.description}

${task.challenge}

<form method="POST" action="/play/${gameName}/task/${task.index}">
	<input name="challenge" />
	<input type="submit" value="Enviar" />
</form>