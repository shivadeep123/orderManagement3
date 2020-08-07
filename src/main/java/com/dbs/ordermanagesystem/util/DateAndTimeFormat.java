package com.dbs.ordermanagesystem.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=DateAndTimeFormatValidator.class)
public @interface DateAndTimeFormat {
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String pattern();

}
