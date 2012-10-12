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
});