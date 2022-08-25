package com.example.wineshop.annotation;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {TYPE, ANNOTATION_TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompareYearValidator.class)
@Documented
public @interface YearValidation {
    String message() default "Invalid year, must be between 1900 and actual year";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String minYear();
}
