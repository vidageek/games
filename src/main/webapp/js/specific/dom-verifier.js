function verify(reference, challenge) {
	if (!reference && !challenge){
		return [];
	}
	if (!reference && challenge){
		return ["Foi encontrada um elemento a mais: " + challenge.nodeName];
	}
	if (reference.nodeName == "#text" && reference.data.replace(/\s+/g, "").length == 0) {
		return [];
	}
	if (reference && !challenge){
		return ["Não foi encontrado o elemento: " + reference.nodeName]; 
	}

	if (reference.nodeName != challenge.nodeName) {
		return ["Esperava encontrar " + reference.nodeName + " mas foi encontrado " + challenge.nodeName];
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
	for (var i = 0; i < referenceChildren.length; i++) {
		var res = verify(referenceChildren[i], childrenOrNull(challengeChildren, i))
		errors = errors.concat(res);
	}
	return errors;
}

function childrenOrNull(array, i) {
	if (!array || i >= array.length) {
		return null;
	}
	return array[i];
}