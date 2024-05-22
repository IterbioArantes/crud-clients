package com.iterbio.crudClients.validation.validators;

import com.iterbio.crudClients.validation.annotations.PastOrPresentWithOneYear;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AgeAtLeastOneYearValidator implements ConstraintValidator<PastOrPresentWithOneYear, LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {

        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        return localDate == null || localDate.isBefore(oneYearAgo) || localDate.isEqual(oneYearAgo);
    }
}
