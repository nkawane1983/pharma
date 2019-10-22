package com.avee.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {
		
	}
	
	public void validateInsertUser(Object object, Errors errors){
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrId", "field.required", "User id required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrName", "field.required", "User name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrPasswd", "field.required", "Password required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrFirstName", "field.required", "First name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrLastName", "field.required", "Last name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrEmail", "field.required", "Email required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrMobile", "field.required", "Mobile number required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrFax", "field.required", "Fax number required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrType", "field.required", "User type required.");
	
	}
	
	public void validateUpdateUser(Object object, Errors errors){
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrId", "field.required", "User id required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrName", "field.required", "User name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrFirstName", "field.required", "First name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrLastName", "field.required", "Last name required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrEmail", "field.required", "Email required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrMobile", "field.required", "Mobile number required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usrFax", "field.required", "Fax number required.");
	
	}
	
}
