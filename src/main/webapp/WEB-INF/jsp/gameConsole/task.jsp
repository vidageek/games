<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header id="game">
<h1>${game.name} Game</h1>
</header>

<div class="row">
	<div class="span5">
		<c:if test="${not empty judgedTask}">
			<div class="reason alert ${judgedTask.ok ? 'alert-success' : 'alert-error'}">
				${judgedTask.reason}
			</div>
		</c:if>  
		
		<form method="POST" action="/play/${gameName}/task/${task.index}">
			<label for="challenge"><strong>${task.challenge}</strong></label>
			<input class="focus span4" name="challenge" value="${challenge}" autocomplete="off"/>
			<input class="btn-primary" type="submit" value="Check!" />
		</form>
		
		<div class="progress">
	    	<div class="bar" style="width: ${(task.index/game.size)*100}%;"></div>
	    </div>
	</div>
	
	<div class="span7">
		${task.description}
	</div>
</div>