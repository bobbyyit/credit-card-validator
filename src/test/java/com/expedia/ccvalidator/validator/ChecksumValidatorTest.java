package com.expedia.ccvalidator.validator;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChecksumValidatorTest  {

    private ChecksumValidator checksumValidator;

    @Before
    public void setUpValidator() {
        checksumValidator = new ChecksumValidator();
    }

    @Test
    public void returnsErrorWhenChecksumFail() {
        Optional<String> result = checksumValidator.validate(BasicValidatorTest.aCreditCart("222222223333", "01-2009"));

        assertThat(result.get(), is("Not a valid credit card"));
    }

    @Test
    public void returnsErrorWhenIllegalCharacters() {
        Optional<String> result = checksumValidator.validate(BasicValidatorTest.aCreditCart("22222222333t", "01-2009"));

        assertThat(result.get(), is("Not a valid credit card"));
    }

    @Test
    public void returnsOkWhenChecksumPassed() {
        Optional<String> result = checksumValidator.validate(BasicValidatorTest.aCreditCart("5160750035000566", "01-2009"));

        assertThat(result.isPresent(), is(false));
    }
}