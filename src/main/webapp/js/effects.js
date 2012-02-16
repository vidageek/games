var effects = effects ? effects : {};

effects.fadeOut = function(element, time) {
	if ($(element).length) {
		$(element).delay(time).fadeOut("slow");
	}
};