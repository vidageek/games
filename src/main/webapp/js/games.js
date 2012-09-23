$(document).ready(function() {
	$('.focus').focus();
	
	$('#logon-provider').modal({backdrop: false, show: false})
	
	if ($('#challenge-result').length) {
		$('#challenge-result').delay(2000).fadeOut("slow");
	}
});