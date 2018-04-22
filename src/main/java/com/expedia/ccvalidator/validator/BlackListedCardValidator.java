package com.expedia.ccvalidator.validator;

import java.util.Optional;

public class BlackListedCardValidator implements Validator {
    @Override
    public Optional<String> validate(CreditCart creditCart) {
        return Optional.empty();
    }
}
