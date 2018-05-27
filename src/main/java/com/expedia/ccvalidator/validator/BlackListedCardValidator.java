package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.BlacklistCreditCards;
import com.expedia.ccvalidator.pojo.CreditCart;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class BlackListedCardValidator implements Validator {
    private BlacklistCreditCards blacklist;

    public BlackListedCardValidator(BlacklistCreditCards blacklist) {
        this.blacklist = blacklist;
    }

    @Override
    public Optional<String> validate(CreditCart creditCart) {
        if (blacklist.getBlacklist().contains(creditCart.getNumber())) {
            return of("Credit card is blacklisted");
        }
        return empty();
    }
}