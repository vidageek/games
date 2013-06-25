
var voidTags = /!DOCTYPE|area|base|br|circle|col|command|embed|hr|img|input|keygen|link|meta|option|param|rect|source|track|wbr/i;

function verify(referenceString, challengeString) {
		
	referenceString = referenceString.replace(/>\s*</g,'><');
	challengeString = challengeString.replace(/>\s*</g,'><');    
    
    var doctypesRef = /^<!DOCTYPE .*>/.exec(referenceString);
    var doctypesChal = /^<!DOCTYPE .*>/.exec(challengeString);
    		
    if (doctypesRef && doctypesRef.length > 0 && (!doctypesChal || doctypesChal.length == 0 || doctypesChal[0] != doctypesRef[0])) {
        return ["Falta declaração de DOCTYPE no início"];
    }
    
    referenceString = referenceString.replace(/^<!DOCTYPE[\s]*(html)?[\s]*>/, "");
    challengeString = challengeString.replace(/^<!DOCTYPE[\s]*(html)?[\s]*>/, "");
    
    
	var wellFormedNessErrors = verifyWellFormedNess(challengeString);
	if (wellFormedNessErrors.length > 0) {
		return wellFormedNessErrors;
	}
	
	var tagPattern = /<(!DOCTYPE|area|base|br|circle|col|command|embed|hr|img|input|keygen|link|meta|option|param|rect|source|track|wbr)([^\/>]*[^\/>]?)>/gm;
	referenceString = referenceString.replace(tagPattern,"<$1$2 />");
	console.log(referenceString);
	challengeString = challengeString.replace(tagPattern,"<$1$2 />");
	console.log(challengeString);
	
	var parser = new DOMParser();
	var reference = parser.parseFromString(referenceString, "text/xml");
	var challenge = parser.parseFromString(challengeString, "text/xml");
	
	return verifySimilarity(reference, challenge);
}


function verifyWellFormedNess(challenge) {
	var tagPattern = /<(\/?[^ \>\/]+)[^>]*>/gm;
	var stack = [];
	var errors = [];
	var tag = tagPattern.exec(challenge);
	var tagCompleta;
	
	while (tag) {
		tagCompleta = tag[0];
		tag = tag[1];
		
		if (tag.charAt(0) == "/") {	
			var top = last(stack);
			if (top == tag.substring(1)){
				stack.pop();
			} else {
				errors.push("Encontrada tag não fechada " + top);
			}
		} else {
			if (!voidTags.test(tag)) {
				stack.push(tag);			
			}
		}
		
		if(tagCompleta.match(/\/\/>$/) != null) {
			errors.push( "Erro sintatico, excesso de barras!");
		}
		
		tag = tagPattern.exec(challenge);
	    

	}
	while(stack.length > 0) {
		errors.push("Encontrada tag não fechada " + stack.pop());
	}
	return errors;
}

function last(array) {
	return array[array.length -1];
}

function verifySimilarity(reference, challenge) {

	if (reference && reference.nodeName == 'parsererror') {
		return [];
	}

	if (challenge && challenge.nodeName == 'parsererror') {
		return [];
	}

	if (reference && reference.nodeName == "#text" && reference.data.replace(/\s+/g, "").length == 0) {
		reference = null;
	}
	if (!reference && !challenge){
		return [];
	}
	if (!reference && challenge){
		return ["Foi encontrado um elemento a mais: " + challenge.nodeName.toLowerCase()];
	}
	if (reference && !challenge){
		return ["Não foi encontrado o elemento: " + reference.nodeName.toLowerCase()]; 
	}



	if (reference.nodeName.toLowerCase() != challenge.nodeName.toLowerCase()) {		
		return ["Esperava encontrar " + reference.nodeName.toLowerCase() + " mas foi encontrado " + challenge.nodeName.toLowerCase()];
	}

	if (reference.attributes) {
		var attsRef = [];
		var attsChal = [];

		for (var i = 0; i < reference.attributes.length; i++) {
			attsRef[reference.attributes[i].nodeName] = reference.attributes[i].nodeValue;
		}
		for (var i = 0; i < challenge.attributes.length; i++) {
			attsChal[challenge.attributes[i].nodeName] = challenge.attributes[i].nodeValue;
		}

		for (var i = 0; i < challenge.attributes.length; i++) {

			if (typeof attsRef[challenge.attributes[i].nodeName] == 'undefined') {
				return ["Atributo inesperado " + challenge.attributes[i].nodeName];
			}
			if (attsRef[challenge.attributes[i].nodeName] != challenge.attributes[i].nodeValue) {
				return ["Atributo " + challenge.attributes[i].nodeName + " deveria valer " + attsRef[challenge.attributes[i].nodeName]];
			}
		}

		for (var i = 0; i < reference.attributes.length; i++) {

			if (typeof attsChal[reference.attributes[i].nodeName] == 'undefined') {
				return ["Atributo " + reference.attributes[i].nodeName + " faltando"];
			}
		}

	}
	else {
		if(challenge.attributes) {
			return ["Atributo inesperado da tag " + challenge.nodeName];
		}
	}


	if (reference.nodeName == "#text" && challenge.nodeName == "#text" && reference.data != challenge.data) {
		return ["Esperava encontrar " + reference.data + " mas foi encontrado " + challenge.data];
	}

	var referenceChildren = reference.childNodes;
	var challengeChildren = challenge.childNodes;

	if (!referenceChildren) {
		return [];
	}

	
	var errors = [];
	var skippedParserErrors = 0;
	for (var i = 0; i < referenceChildren.length; i++) {
		if (childrenOrNull(challengeChildren, i) && childrenOrNull(challengeChildren, i).nodeName == 'parsererror') {
			skippedParserErrors += 1;
			return ["Erro sintático"];
		}
		errors = errors.concat(verifySimilarity(referenceChildren[i], childrenOrNull(challengeChildren, i + skippedParserErrors)));
	}
	
	for (var i = 0; i < challengeChildren.length - referenceChildren.length; i++){
		if (childrenOrNull(challengeChildren, i + referenceChildren.length) && childrenOrNull(challengeChildren, i + referenceChildren.length).nodeName == 'parsererror') {
			skippedParserErrors += 1;
			return ["Erro sintático"];
		}
		errors = errors.concat(verifySimilarity(null,childrenOrNull(challengeChildren, i + skippedParserErrors + referenceChildren.length)));
	}
	return errors;
}

function childrenOrNull(array, i) {
	if (!array || i >= array.length) {
		return null;
	}
	return array[i];
}
