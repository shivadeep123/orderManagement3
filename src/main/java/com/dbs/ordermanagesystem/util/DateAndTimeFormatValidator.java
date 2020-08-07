package com.dbs.ordermanagesystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class DateAndTimeFormatValidator implements ConstraintValidator<DateAndTimeFormat, String>{
	
	private String pattern;

	@Override
	public void initialize(DateAndTimeFormat constraintAnnotation) {
		pattern = constraintAnnotation.pattern();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isEmpty(value) || isValidDate(value,pattern);
	}
	
	
	public boolean isValidDate(String dateString, String pattern) {
		boolean valid = true;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		df.setLenient(false);
		try {
			dateString  = dateString.trim();
			df.parse(dateString);
		}catch(ParseException e) {
			valid = false;
		}
		return valid;
	}

}
