$(document).ready(function() {
	$('.focus').focus();
	
	$('#logon-provider').modal({backdrop: false, show: false})
	
	if ($('.reason.alert.alert-success').length) {
		$('.reason.alert.alert-success').delay(2000).fadeOut("slow");
	}
});