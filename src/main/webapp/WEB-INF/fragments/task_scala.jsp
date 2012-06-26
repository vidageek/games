<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="span6">
		<c:if test="${not empty judgedTask}">
			<div class="reason alert ${judgedTask.ok ? 'alert-success' : 'alert-error'}">
				${judgedTask.reason}
			</div>
		</c:if>  
		
		<form method="POST" action="/play/${gameName}/task/${task.index}">
			<label for="challenge"><strong>${task.challenge}</strong></label>
			<textarea class="focus span6" name="challenge" id="challenge" value="${challenge}" autocomplete="off"></textarea>
			<input class="btn-primary" type="submit" value="Check!" />
		</form>
		
		<div class="progress">
	    	<div class="bar" style="width: ${(task.index/game.size)*100}%;"></div>
	    </div>
	</div>
	
	<div class="span6">
		${task.description}
	</div>
	
</div>