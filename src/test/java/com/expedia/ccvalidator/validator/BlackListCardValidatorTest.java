package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.BlacklistCreditCards;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Optional;

import static com.expedia.ccvalidator.validator.BasicValidatorTest.aCreditCart;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BlackListCardValidatorTest {

    private BlackListedCardValidator blackListedCardValidator;

    @Before
    public void setUp() throws Exception {
        URL url = getClass().getResource("/black-listed-credit-cards.json");
        ObjectMapper mapper = new ObjectMapper();
        BlacklistCreditCards blacklist = mapper.readValue(new File(url.getPath()), BlacklistCreditCards.class);

        blackListedCardValidator = new BlackListedCardValidator(blacklist);
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