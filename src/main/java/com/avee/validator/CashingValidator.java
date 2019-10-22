package com.avee.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CashingValidator implements Validator{



@Override
public boolean supports(Class<?> clazz) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void validate(Object target, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "refNo", "field.required", "Reference No. required.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zReading", "field.required", "zReading required.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sales", "field.required", "Number of sale must be entered even if it is Zero.");
	///ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zReadPrivateValue", "field.required", "Private Prescription value must be entered even if it is zero.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zReadLevy", "field.required", "Levies as per Z-Read Value must be entered even if it is zero.");
}
}
