package com.johnny.transactiondemo.annotations.validations;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.johnny.transactiondemo.enumerations.Gender;

public class GenderValidatorTest {
    @Test
    void whenIsValidGender() throws Exception {
        GenderValidator validator = new GenderValidator();

        assertThat(validator.isValid(Gender.M.name(), null)).isTrue();
        assertThat(validator.isValid(Gender.F.name(), null)).isTrue();
    }

    @Test
    void invalidGender() throws Exception {
        GenderValidator validator = new GenderValidator();
        assertThat(validator.isValid("Others", null)).isFalse();
    }

    @Test
    void initGenderValidator() throws Exception {
        GenderValidator validator = new GenderValidator();
        validator.initialize(null);
    }
}
