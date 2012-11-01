function verify(reference, challenge, challengeString) {
	return verifySimilarity(reference, challenge).concat(verifyWellFormedNess(challengeString));
}

function verifyWellFormedNess(challenge) {
	var voidTags = /area|base|br|col|command|embed|hr|img|input|keygen|link|meta|param|source|track|wbr/i;
	var tagPattern = /<(\/?[^ \>\/]+)/gm;
	var stack = [];
	var errors = [];
	var tag = tagPattern.exec(challenge);
	while (tag) {
		tag = tag[1]
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
		// challenge = challenge.substring(challenge);
		tag = tagPattern.exec(challenge);
	}
	while(stack.length > 0) {
		errors.push("Encontrada tag não fechada " + stack.pop());
		console.log(2);
	}
	return errors;
}

function last(array) {
	return array[array.length -1];
}

function verifySimilarity(reference, challenge) {
	if (!reference && !challenge){
		return [];
	}
	if (!reference && challenge){
		return ["Foi encontrada um elemento a mais: " + challenge.nodeName.toLowerCase()];
	}
	if (reference.nodeName == "#text" && reference.data.replace(/\s+/g, "").length == 0) {
		return [];
	}
	if (reference && !challenge){
		return ["Não foi encontrado o elemento: " + reference.nodeName.toLowerCase()]; 
	}

	if (reference.nodeName != challenge.nodeName) {
		return ["Esperava encontrar " + reference.nodeName.toLowerCase() + " mas foi encontrado " + challenge.nodeName.toLowerCase()];
	}

	if (reference.nodeName == "#text" && challenge.nodeName == "#text" && reference.data.replace(/\s+/g, "") != challenge.data.replace(/\s+/g, "")) {
		return ["Esperava encontrar " + reference.data + " mas foi encontrado " + challenge.data];
	}
	
	var referenceChildren = reference.childNodes;
	var challengeChildren = challenge.childNodes;
	
	if (!referenceChildren) {
		return [];
	}
	
	var errors = [];
	for (var i = 0; i < Math.max(referenceChildren.length, challengeChildren.length); i++) {
		var res = verifySimilarity(childrenOrNull(referenceChildren[i]), childrenOrNull(challengeChildren, i))
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