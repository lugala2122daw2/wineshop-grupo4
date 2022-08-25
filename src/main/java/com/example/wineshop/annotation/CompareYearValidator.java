package com.example.wineshop.annotation;

import javax.validation.*;
import java.time.LocalDate;

public class CompareYearValidator implements ConstraintValidator<YearValidation, String> {

    private int actualYear;
    private int minYear;

    @Override
    public void initialize(final YearValidation constraintAnnotation){
        minYear = Integer.parseInt(constraintAnnotation.minYear());
        actualYear = LocalDate.now().getYear();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int val = Integer.parseInt(value);
        if (val >= minYear && val <= actualYear) return true;
        else return false;
    }

}
