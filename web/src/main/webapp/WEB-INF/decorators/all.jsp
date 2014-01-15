<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://vidageek.net/autoweb" prefix="aw"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="google-site-verification"
	content="FjDxiddkSQXOHTFWCpYqrXpjj7wCoCcX1krRxTCHuq0" />
<meta name="google-site-verification" content="igmWNUz0B_VblujqXG47m32FBgjyQ5Zc2Oq-3YzSZv8" />
<title><decorator:title /></title>
<decorator:head />
	<aw:css url="/css/games.css" />
	<c:if test="${not empty gameName}">
		<aw:css url="/css/${gameName}.css" />
	</c:if>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-1508082-6' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
	
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<c:if test="${not empty gameName}">
					<a class="brand" href="/play/${gameName}">${game.name} Game</a>
				</c:if>
			    <c:choose>
                    <c:when test="${empty player}">
                    	<ul class="nav">
                        	<li class="active"><a id="select-a-provider" class="brand pull-right nav-link" data-toggle="modal" href="#logon-provider">Login</a></li>
                        	<li><a href="/about">Sobre</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <div class="nav-collapse">
                            <ul class="nav">
                            	<li id="level">
                            		<a href="#" style="
                            		background: -moz-linear-gradient(left center, #8CF ${player.progress}%, #EEE ${player.progress}%);
    								background: -webkit-gradient(linear, left top, right top, color-stop(${player.progress}%,#8CF), color-stop(${player.progress}%,#EEE));
    								background: linear-gradient(left center, #8CF ${player.progress}%, #EEE ${player.progress}%);
                            		">
                            		<span>${player.level}</span></a>
                            	</li>
                                <li class="active">
                                    <a id="logged" class="pull-right nav-link" href="/progress">${player.email}</a>
                                </li>
                                <c:if test="${not empty player.lastTask}">
	                                <li>
	                                    <a class="pull-right nav-link" href="${player.lastTask}">Última Jogada</a>
	                                </li>
                                </c:if>
                                <li><a href="/about">Sobre</a></li>
                                <li class="divider-vertical"></li>
                                <li>
                                    <a class="pull-right nav-link" href="<c:url  value="/logout" />">Logout</a>
                                </li>
                            </ul>
                        </div>
                    </c:otherwise>
			    </c:choose>
			</div>
			<div class="ribbon-holder" >
				<a href="https://github.com/vidageek/games" class="ribbon"> 
					<span class="text">Ajude-nos no GitHub</span>
				</a>
			</div>
		</div>
	</div>
	<div id ="main-content" class="container">
		<c:if test="${not empty notice}">
			<div class="alert alert-info">
				${notice}
			</div>
		</c:if>
		<decorator:body />
	</div>

	<section>
		<div id="logon-provider" class="modal hide fade in">
			<div class="modal-body">
				<a class="close" data-dismiss="modal">X</a>
				<form action="/player" method="POST">
					<label for="email">Email:</label>
					<input name="email" />
					<input type="submit" value="Logar" />
				</form>
				
				<div class="alert alert-info">
					<a href="/senha">Porquê não preciso colocar minha senha?</a>
				</div>
			</div>
		</div>
	</section>
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<aw:js url="/js/games.js" />

	<c:if test="${not empty gameName}">
		<aw:js url="/js/${gameName}.js" />
	</c:if>
	
</body>
</html>
