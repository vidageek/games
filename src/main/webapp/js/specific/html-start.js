var code = "";
var editor;

$(document).ready(function(){
	var challenge = document.getElementById("challenge")
	editor = CodeMirror.fromTextArea(challenge, {
	  lineNumbers: true,
	  autofocus: true,
	  matchBrackets: true,
	  mode: "text/html"
	});
	
	window.setInterval(function() {
		var value = editor.getValue();
		if (!(value == code)) {
			code = value;
			$('#render-challenge').contents().find("body").html(code);
		}
	} ,100);
});