package com.expedia.ccvalidator.validator;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BasicValidatorTest {

    private BasicValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new BasicValidator();
    }

    @Test
    public void errorWhenIllegalCharacter() {
        Optional<String> response = validator.validate(aCreditCart("a-incorrect-numb", "07-18"));

        assertThat(response.get(), is("Contains illegal characters"));
    }

    @Test
    public void errorWhenNot16Character() {
        Optional<String> response = validator.validate(aCreditCart("12345678912345", "07-18"));

        assertThat(response.get(), is("Does not contain 16 characters"));
    }

    @Test
    public void errorWhenNotVisaMaster() {
        Optional<String> response = validator.validate(aCreditCart("8256456776547654", "07-18"));

        assertThat(response.get(), is("Only Visa/Mastercard accepted"));
    }

    @Test
    public void noErrorWhenVisa() {
        Optional<String> response = validator.validate(aCreditCart("4456456776547654", "07-18"));

        assertThat(response.isPresent(), is(false));
    }

    @Test
    public void noErrorWhenMastercard() {
        Optional<String> response = validator.validate(aCreditCart("5256456776547654", "07-18"));

        assertThat(response.isPresent(), is(false));
    }

    @Test
    public void noErrorWhenExpirationDateAfterToday() {
        Optional<String> response = validator.validate(aCreditCart("5256456776547654", "07-18"));

        assertThat(response.isPresent(), is(false));
    }

    @Test
    public void errorWhenExpirationDateBeforeToday() {
        Optional<String> response = validator.validate(aCreditCart("5256456776547654", "07-2017"));

        assertThat(response.get(), is("credit card has expired"));
    }

    private CreditCart aCreditCart(String number, String expiration) {
        return new CreditCart(number, expiration);
    }
}