package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.CreditCard;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.getInstance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BasicValidatorTest {

    private BasicValidator validator;

    @Before
    public void setUpValidator() {
        validator = new BasicValidator();
    }

    @Test
    public void errorWhenIllegalCharacter() {
        Optional<String> response = validator.validate(aCreditCart("a-incorrect-numb", nextMonthDate()));

        assertThat(response.get(), is("Contains illegal characters"));
    }

    @Test
    public void errorWhenNot16Character() {
        Optional<String> response = validator.validate(aCreditCart("12345678912345", nextMonthDate()));

        assertThat(response.get(), is("Does not contain 16 characters"));
    }

    @Test
    public void errorWhenNotVisaMaster() {
        Optional<String> response = validator.validate(aCreditCart("8256456776547654", nextMonthDate()));

        assertThat(response.get(), is("Not Visa/Mastercard"));
    }

    @Test
    public void noErrorWhenVisa() {
        Optional<String> response = validator.validate(aCreditCart("4456456776547654", nextMonthDate()));

        assertThat(response.isPresent(), is(false));
    }

    @Test
    public void noErrorWhenMastercard() {
        Optional<String> response = validator.validate(aCreditCart("5256456776547654", nextMonthDate()));

        assertThat(response.isPresent(), is(false));
    }

    @Test
    public void noErrorWhenExpirationDateAfterToday() {
        Optional<String> response = validator.validate(aCreditCart("5256456776547654", nextMonthDate()));

        assertThat(response.isPresent(), is(false));
    }

    @Test
    public void errorWhenExpirationDateBeforeToday() {
        Optional<String> response = validator.validate(aCreditCart("5256456776547654", "07-2015"));

        assertThat(response.get(), is("Credit card has expired"));
    }

    public static CreditCard aCreditCart(String number, String expiration) {
        return new CreditCard(number, expiration);
    }

    private String nextMonthDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM-YYYY");
        Calendar cal = getInstance();
        cal.set(MONTH, cal.get(MONTH) + 1);
        return dateFormat.format(cal.getTime());
    }
}