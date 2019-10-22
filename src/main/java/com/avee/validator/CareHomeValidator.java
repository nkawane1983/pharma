package com.avee.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CareHomeValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchId", "field.required", "Branch Name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "Care Home Name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addLine1", "field.required", "Address required.");
	}

}
