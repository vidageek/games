<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<c:forEach items="${task.extraData.commits}" var="c">
	<div class="span3">
		${c.branch}:
		<ul>
			<c:forEach items="${c.commits}" var="commit">
				<li>${commit}</li>
			</c:forEach>
		</ul>
	</div>
	</c:forEach>
</div>
<div class="row">
	Branch atual: ${task.extraData.branch}
</div>
<div class="row">
	<div class="span6">
		<c:if test="${not empty judgedTask}">
			<div id="challenge-result" class="reason alert ${judgedTask.ok ? 'alert-success' : 'alert-error'}">
				${judgedTask.reason}
			</div>
		</c:if>		
		<form class="challenge" method="POST" action="/play/${gameName}/task/${task.index}">
			<label for="challenge"><strong>${task.challenge}</strong></label>
			<input class="focus span6" name="challenge" id="challenge" autocomplete="off" value="${challenge}"/>
			<input id="challenge-submit" class="btn btn-primary" type="submit" value="Next! (ctrl + enter)" />
		</form>
		
		<div class="progress">
	    	<div class="bar" style="width: ${(task.index/game.size)*100}%;"></div>
	    </div>
	</div>
	
	<div class="span6">
		<h2>${task.groupName}</h2>
		${task.description}
	</div>
	
</div>