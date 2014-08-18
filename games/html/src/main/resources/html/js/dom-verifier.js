var tagRegexString = "(!DOCTYPE|area|base|br|circle|col|command|embed|hr|img|input|keygen|link|meta|option|param|rect|source|track|wbr)";
var voidTags = new RegExp(tagRegexString,"i");

function verify(referenceString, challengeString) {
		
	referenceString = removeSpacesBetweenTags(referenceString);
	challengeString = removeSpacesBetweenTags(challengeString);
    	
	var parser = new DOMParser();
	var reference = parser.parseFromString(referenceString, "text/xml");
	var challenge = parser.parseFromString(challengeString, "text/xml");
	
	console.log(reference);
	console.log(challenge);
	
	
	return verifySimilarity(reference, challenge);
}


function removeSpacesBetweenTags(htmlStr)
{
	return htmlStr.replace(/>\s*([^\s<][^<]*[^\s<])?\s*</g,'>$1<');
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
