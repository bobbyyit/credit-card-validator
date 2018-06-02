package com.expedia.ccvalidator.validator;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static com.expedia.ccvalidator.validator.BasicValidatorTest.aCreditCart;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BlackListCardValidatorTest {

    private BlackListedCardValidator blackListedCardValidator;

    @Before
    public void setUpValidator() throws Exception {
        BlacklistLoader blacklistLoader = new BlacklistLoader("/black-listed-credit-cards.json");

        blackListedCardValidator = new BlackListedCardValidator(blacklistLoader.load());
    }

    @Test
    public void returnsErrorForBlacklistedCard() {
        Optional<String> result = blackListedCardValidator.validate(aCreditCart("4711111111111111", "02-2014"));

        assertThat(result.get(), is("Credit card is blacklisted"));
    }

    @Test
    public void returnsOkForNonBlacklistedCard() {
        Optional<String> result = blackListedCardValidator.validate(aCreditCart("5145454545452323", "02-2014"));

        assertThat(result.isPresent(), is(false));
    }
}