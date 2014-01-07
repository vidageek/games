<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<div class="span6">
		
		<iframe id="render-challenge" class="game-frame"></iframe>
		
		<div id="challenge-result"></div>
		
		<form class="challenge" method="POST" action="/play/${gameName}/task/${task.index}">
			<label for="challenge"><strong>${task.challenge}</strong></label>
			<textarea class="focus span6" name="challenge" id="challenge" autocomplete="off">
${challenge}</textarea>
			<input id="challenge-submit" disabled class="btn btn-primary disabled" type="submit" value="Next! (ctrl + enter)" />
		</form>
		
		<div class="progress">
	    	<div class="bar" style="width: ${(task.index/game.size)*100}%;"></div>
	    </div>
	</div>
	
	<div class="span6">
		<iframe id="render-answer" class="game-frame" src="/play/html/resource/${task.resource}"></iframe>
	
		<h2>${task.groupName}</h2>
		${task.description}
	</div>
	
</div>