package com.expedia.ccvalidator.validator;

import java.util.Optional;

public interface Validator {
    Optional<String> validate(CreditCart creditCart);
}
