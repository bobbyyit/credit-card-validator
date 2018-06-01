package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.CreditCard;

import java.util.Optional;

public interface Validator {
    Optional<String> validate(CreditCard creditCard);
}
