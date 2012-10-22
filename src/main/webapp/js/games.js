startTime = new Date().getTime() / 1000

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
		$('<input>').attr({
			type: 'hidden',
			name: 'activeTime',
			value: Math.floor((new Date().getTime() / 1000) - startTime)
		}).appendTo('form.challenge');
	});

});