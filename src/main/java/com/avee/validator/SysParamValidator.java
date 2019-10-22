package com.avee.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SysParamValidator implements Validator{
	
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parameterName", "field.required", "name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parameterValue", "field.required", "value required.");
	}
}
