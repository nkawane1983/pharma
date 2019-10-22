package com.avee.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class NHSValidator  implements Validator{
	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rolId", "field.required", "Role id required.");
		
	}
}
