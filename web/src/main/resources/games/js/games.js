startTime = new Date().getTime() / 1000

function helloWorld(){
	return "Hello world!";
}

$(document).ready(function() {
	if (!($.browser.webkit || $.browser.mozilla)) {
		$('#main-content').prepend('<div class="alert alert-danger">Parece que o seu browser não é suportado ' +
		'e alguns Jogos podem não funcionar corretamente. Utilize a última versão de ' +
		'<a href="http://www.google.com/chrome">Chrome</a> ou ' +
		'<a href="https://www.mozilla.org/en-US/firefox/new/">Firefox</a></div>')
	}

	$('.focus').focus();
	
	$('#logon-provider').modal({backdrop: false, show: false})	

	$('form.challenge').submit(function(e){
		if ($('form.challenge input[disabled]').length == 0) {
			$('<input>').attr({
				type: 'hidden',
				name: 'activeTime',
				value: Math.floor((new Date().getTime() / 1000) - startTime)
			}).appendTo('form.challenge');
		} else {
			return false;
		}
	});
	
	$('body').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if((keycode == '13' || keycode == '10') && event.ctrlKey){
			$('form.challenge').submit();	
			event.preventDefault();
		}
	});
	
	if($('#contributors').length > 0) {	
		$.getJSON("https://api.github.com/repos/vidageek/games/contributors?callback=?",
			function(data){
				var contributors = "";
				$.each(data.data, function(i, e) {
					contributors += '<div>' + 
						'<img src="https://secure.gravatar.com/avatar/' + e.gravatar_id +'" />' +
						'<a class="btn btn-info" href="https://github.com/'+ e.login + '">' + e.login + '</a>' +
						'</div>'
				});
				$('#contributors').html(contributors);
		});
	}
});
