$.fn.validate = function() {
	var requiredValidationResult = true;
	var requiredInputs = $(this).find("input:required");
	requiredInputs.each(function() {
		if ($(this).val().trim() == '') {
			requiredValidationResult = false;
			if (!$(this).hasClass('is-invalid')) {
				$(this).addClass('is-invalid');
			}
		} else {
			if ($(this).hasClass('is-invalid')) {
				$(this).removeClass('is-invalid');
			}
		}
	});

	if (!requiredValidationResult) {
		toastr.error('Please fill required values.');
	}

	var numericValidationResult = true;
	var numericInputs = $(this).find("input[class*='constraint-numeric']");
	numericInputs.each(function() {
		if (!isNumeric($(this).val())) {
			numericValidationResult = false;
			if (!$(this).hasClass('is-invalid')) {
				$(this).addClass('is-invalid');
			}
		} else {
			if ($(this).hasClass('is-invalid')) {
				$(this).removeClass('is-invalid');
			}
		}
	});
	return requiredValidationResult && numericValidationResult;
}

$.fn.serializeObject = function() {
	var o = {};
	//    var a = this.serializeArray();
	$(this).find('input[type="hidden"], input[type="text"], input[type="password"], input[type="checkbox"]:checked, input[type="radio"]:checked, select').each(function() {
		if ($(this).attr('type') == 'hidden') { //if checkbox is checked do not take the hidden field
			var $parent = $(this).parent();
			var $chb = $parent.find('input[type="checkbox"][name="' + this.name.replace(/\[/g, '\[').replace(/\]/g, '\]') + '"]');
			if ($chb != null) {
				if ($chb.prop('checked')) return;
			}
		}
		if (this.name === null || this.name === undefined || this.name === '')
			return;
		var elemValue = null;
		if ($(this).is('select'))
			elemValue = $(this).find('option:selected').val();
		else elemValue = this.value;
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(elemValue || '');
		} else {
			o[this.name] = elemValue || '';
		}
	});
	return o;
}

var isNumeric = function(str) {
	if (typeof str != "string") return false // we only process strings!  
	return !isNaN(str) && // use type coercion to parse the _entirety_ of the string (`parseFloat` alone does not do this)...
		!isNaN(parseFloat(str)) // ...and ensure strings of whitespace fail
};