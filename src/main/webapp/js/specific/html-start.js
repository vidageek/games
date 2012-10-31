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
			var challenge = $('#render-challenge').contents().find("body").html(code);
			var errors = verify($('#render-answer').contents().find("body")[0], challenge[0]);
			if (errors.length > 0){
				var errorHtml = "<ul>" + $.map(errors, function(e){return "<li>" + e + "</li>"}).join("") + "</ul>"
				$('#challenge-result').removeClass("alert-success").addClass("reason alert alert-error").html(errorHtml);
			} else {
				$('#challenge-result').removeClass("alert-error").addClass("alert-success").html("Ok!");
			}
		}
	} ,100);
});