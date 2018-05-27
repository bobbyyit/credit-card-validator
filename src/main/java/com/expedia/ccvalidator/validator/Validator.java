package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.CreditCart;

import java.util.Optional;

public interface Validator {
    Optional<String> validate(CreditCart creditCart);
}
