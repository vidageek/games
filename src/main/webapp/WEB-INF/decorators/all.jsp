<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://vidageek.net/autoweb" prefix="aw"%>
<%@ taglib uri="http://vidageek.net/games" prefix="g"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="google-site-verification"
	content="FjDxiddkSQXOHTFWCpYqrXpjj7wCoCcX1krRxTCHuq0" />
<meta name="google-site-verification" content="igmWNUz0B_VblujqXG47m32FBgjyQ5Zc2Oq-3YzSZv8" />
<title><decorator:title /></title>
<aw:css url="/css/games-packaged.css" />
<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<g:specificCss />
	
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
				<c:if test="${player.authorized}">
					<div class="nav-collapse">
						<ul class="nav">
							<li class="active">
								<a id="logged" class="brand pull-right" href="#logado">${player.userName}</a>
							</li>
							<li class="divider-vertical"></li>
							<li>
								<a id="logged" class="brand pull-right" href="<c:url  value="/auth/logout" />">Logout</a>
							</li>
						</ul>
					</div>
				</c:if>
				<c:if test="${not player.authorized}">
					<a id="select-a-provider" class="brand pull-right" data-toggle="modal" href="#logon-provider">Logar...</a>
				</c:if>
			</div>
			<div class="ribbon-holder" >
				<a href="https://github.com/vidageek/games" class="ribbon"> 
					<span class="text">Ajude-nos no GitHub</span>
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<decorator:body />
	</div>

	<section>
		<div id="logon-provider" class="modal hide fade in">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">×</a>
				<h3>Selecione por onde deseja logar</h3>
			</div>
			<div class="modal-body">
				<ul class="nav nav-tabs nav-stacked">
					<li>
						<a href="<c:url  value="/auth/provider/twitter" />?backUrl=<%= request.getRequestURL() %>">Twitter</a>
					</li>
				</ul>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
			</div>
		</div>
	</section>
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<aw:js url="/js/games-packaged.js" />
	<aw:js url="/js/bootstrap-modal.js" />

	<g:specificJs />
	
</body>
</html>
