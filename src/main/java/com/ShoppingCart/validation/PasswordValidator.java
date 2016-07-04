package com.ShoppingCart.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>{

	private Pattern pattern;
	private Matcher matcher;
	/* a digit must occur at least once
	   a lower case letter must occur at least once
	   an upper case letter must occur at least once
	   a special character must occur at least once
	   no whitespace allowed in the entire string
	   at least eight places */
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}(?=\\S+$)$";
	
	@Override
	public void initialize(ValidPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (validatePassword(value));
	}

	private boolean validatePassword(String value) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}

}
