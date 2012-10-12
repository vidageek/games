startTime = new Date().getTime() / 1000

$(document).ready(function() {
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