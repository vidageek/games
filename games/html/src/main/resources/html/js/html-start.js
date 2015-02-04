var code = "";
var editor;

function envelope(code) {
  return  beforeCode + code + afterCode;
}

function setIframeContent(iframe, content) {
  var ifr = iframeDocument(iframe);
  ifr.open();
  ifr.write(envelope(content));
  ifr.close();
}

function iframeDocument(iframe) {
  return window[iframe].contentDocument || window[iframe].contentWindow.document;
}

function resolveError(error, map) {
  var template = map[error.type];
  var errorHTML = template.replace(/\[\[([^\]]+)\]\]/g, function(_, term) {
    var terms = term.indexOf(".") > -1 ? term.split(".") : [term];
    var value = error;
    while(terms.length > 0) {
      value = value[terms.splice(0,1)[0]];
    }
    return value;
  });
  return errorHTML
};

function syntaxErrorsOf(code){
  var syntaxError = Slowparse.HTML(document, envelope(code));
  if (syntaxError.error){
    return [resolveError(syntaxError.error, errorMap)];
  }
  return [];
}

function domErrors(){

  var reference = iframeDocument("render-answer");
  var challenge = iframeDocument("render-challenge");

  return domDiff.compare(reference, challenge, {stripSpaces: true}).getDifferences().map(function(e) {return e.message;});
}

$(document).ready(function(){

  prettyPrint();

  var challenge = document.getElementById("challenge")
  if (challenge != null) {
    editor = CodeMirror.fromTextArea(challenge, {
      lineNumbers: true,
      autofocus: true,
      matchBrackets: true,
      mode: "text/html",
      theme: "high-contrast"
    });	

    window.beforeCode = $('#render-answer').attr('data-before');
    window.afterCode = $('#render-answer').attr('data-after');


    $.get($('#render-answer').attr("data-src"), function (data) {
      setIframeContent("render-answer", data);
    });

    $("#render-challenge").on("load", function(){

      var errors = syntaxErrorsOf(code); 

      if (errors.length == 0) {
        errors = domErrors();
      }

      if (errors.length > 0){
        var errorHtml = "<ul>" + errors.map(function(e){return "<li>" + e + "</li>";}).join("") + "</ul>"
        $('#challenge-result').removeClass("alert-success").addClass("reason alert alert-error").html(errorHtml);
        $('#challenge-submit').addClass("disabled").attr("disabled", "disabled");
      } else {
        $('#challenge-result').removeClass("alert-error").addClass("alert-success").html("Ok!");
        $('#challenge-submit').removeClass("disabled").removeAttr("disabled");
      }

    });

    function checkCode() {
      var value = editor.getValue();

      if (value != code) {
        code = value;

        setIframeContent('render-challenge', code); 


      }
    }
    window.setInterval(checkCode, 100);
  }
});
