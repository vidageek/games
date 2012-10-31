function verify(reference, challenge) {
	if (!reference || !challenge){
		return [];
	}

	if (!areEqual(reference, challenge)) {
		return ["Esperava encontrar " + reference.nodeName + " mas foi encontrado " + challenge.nodeName];
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

function areEqual(n1, n2) {
	return n1.nodeName === n2.nodeName; 	
}