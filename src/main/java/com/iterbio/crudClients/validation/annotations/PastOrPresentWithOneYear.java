package com.iterbio.crudClients.validation.annotations;

import com.iterbio.crudClients.validation.validators.AgeAtLeastOneYearValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.PastOrPresent;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { AgeAtLeastOneYearValidator.class })
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@PastOrPresent
@ReportAsSingleViolation
public @interface PastOrPresentWithOneYear {

    String message() default "The date must be at least 1 year before the current date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
