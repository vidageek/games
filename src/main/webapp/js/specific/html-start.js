var code = "";
var editor;

$(document).ready(function(){
	var challenge = document.getElementById("challenge")
	editor = CodeMirror.fromTextArea(challenge, {
	  lineNumbers: true,
	  autofocus: true,
	  matchBrackets: true,
	  mode: "text/html",
	  theme: "high-contrast"
	});
	
	function xml2string(node) {
   		if (typeof(XMLSerializer) !== 'undefined') {
      		var serializer = new XMLSerializer();
     	 	return serializer.serializeToString(node);
   		} else if (node.xml) {
      		return node.xml;
   		}
	}
	
	
	function checkCode() {
		var value = editor.getValue();
		if (!(value == code)) {
			code = value;
			
			$('#render-challenge').contents().find("html").html(code); 
			
			var errors = verify(xml2string($('#render-answer').contents()[0]), code);
			if (errors.length > 0){
				var errorHtml = "<ul>" + $.map(errors, function(e){return "<li>" + e + "</li>"}).join("") + "</ul>"
				$('#challenge-result').removeClass("alert-success").addClass("reason alert alert-error").html(errorHtml);
				$('#challenge-submit').addClass("disabled").attr("disabled", "disabled");
			} else {
				$('#challenge-result').removeClass("alert-error").addClass("alert-success").html("Ok!");
				$('#challenge-submit').removeClass("disabled").removeAttr("disabled");
			}
		}
	}
	window.setInterval(checkCode, 100);
});