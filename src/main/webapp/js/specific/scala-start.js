$(document).ready(function(){
	var challenge = document.getElementById("challenge")
	if (challenge) {
		var editor = CodeMirror.fromTextArea(challenge, {
		  lineNumbers: true,
		  autofocus: true,
		  matchBrackets: true,
		  theme: "eclipse",
		  mode: "text/x-scala"
		});
	}
	
	$('body').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13' && event.ctrlKey){
			$('form.challenge').submit();	
			event.preventDefault();
		}
	});
});