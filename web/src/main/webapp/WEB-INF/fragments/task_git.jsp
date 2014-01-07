<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="git">


<div class="row">
	<c:forEach items="${task.extraData.commits}" var="c">
	<div class="span3 branch branch-${c.branch}">
		<span class="branch">${c.branch}</span>${task.extraData.branch == c.branch ? ' <span class="active">(ativo)</span>' : ''}
		<ul>
			<c:forEach items="${c.commits}" var="commit">
				<li>${commit}</li>
			</c:forEach>
		</ul>
	</div>
	</c:forEach>
</div>

<div class="row">
	<div class="span4">
		<c:if test="${not empty task.extraData.files['candidate']}">
			Changes to be commited:
			<ul class="files-stage">
			<c:forEach items="${task.extraData.files['candidate']}" var="file">
				<li>${file}</li>
			</c:forEach>
			</ul>
		</c:if>
	</div>
	<div class="span4">
		<c:if test="${not empty task.extraData.files['modified']}">
			Modified:
			<ul class="files-modified">
			<c:forEach items="${task.extraData.files['modified']}" var="file">
				<li>${file}</li>
			</c:forEach>
			</ul>
		</c:if>
	</div>
	<div class="span4">
		<c:if test="${not empty task.extraData.files['untracked']}">
			Untracked:
			<ul class="files-untracked">
			<c:forEach items="${task.extraData.files['untracked']}" var="file">
				<li>${file}</li>
			</c:forEach>
			</ul>
		</c:if>
	</div>
</div>

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
			<input class="focus span5" name="challenge" id="challenge" autocomplete="off" value="${challenge}"/>
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