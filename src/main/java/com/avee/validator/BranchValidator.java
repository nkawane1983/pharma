package com.avee.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BranchValidator implements Validator {
	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rolId",
		 * "field.required", "Role id required.");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchName",
		 * "field.required", "name required.");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchManager",
		 * "field.required", "name required.");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managerMobile",
		 * "field.required", "name required.");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
		 * "field.required", "name required.");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managerTelephone",
		 * "field.required", "name required.");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Email",
		 * "field.required", "name required.");
		 */}
}
