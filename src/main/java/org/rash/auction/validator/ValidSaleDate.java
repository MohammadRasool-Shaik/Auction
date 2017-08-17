package org.rash.auction.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SaleDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSaleDate {

    String message() default "Verify start date and end date as per the system date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
