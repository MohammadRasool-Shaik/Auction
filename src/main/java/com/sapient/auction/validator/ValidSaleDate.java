package com.sapient.auction.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SaleDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSaleDate {

	String message() default "Verify start date and end date as per the system date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
