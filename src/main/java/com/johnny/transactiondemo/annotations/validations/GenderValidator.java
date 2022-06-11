package com.johnny.transactiondemo.annotations.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.johnny.transactiondemo.enumerations.Gender;

public class GenderValidator implements ConstraintValidator<GenderValidation, String>{
    @Override
    public void initialize(GenderValidation gender) {
    }
    @Override
    public boolean isValid(String gender, ConstraintValidatorContext cxt) {
        try {
            Gender.valueOf(gender);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
