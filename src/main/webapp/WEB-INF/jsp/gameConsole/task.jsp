<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty judgedTask}">
	<div class="reason">
		<p>Resposta:</p>
		<p>${judgedTask.reason}</p>
	</div>
</c:if>  
<div>
${task.description}
</div>
<div>
${task.challenge}

<form method="POST" action="/play/${gameName}/task/${task.index}">
	<input name="challenge" />
	<input type="submit" value="Enviar" />
</form>
</div>
