package com.expedia.ccvalidator.validator;

import java.util.Optional;

import static java.lang.Integer.valueOf;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class BasicValidator implements Validator {
    @Override
    public Optional<String> validate(CreditCart creditCart) {
        String creditCartNumber = creditCart.getNumber();
        if (creditCartNumber.length() != 16) {
            return of("Does not contain 16 characters");
        }
        if (!isNumeric(creditCartNumber)) {
            return of("Contains illegal characters");
        }

        int firstTwoDigit = valueOf(creditCartNumber.substring(0, 2));
        if (!(isVisa(firstTwoDigit) || isMastercard(firstTwoDigit))) {
            return of("Only Visa/Mastercard accepted");
        }

        return empty();
    }

    private boolean isVisa(int firstTwoDigit) {
        return firstTwoDigit >= 40 && firstTwoDigit <= 49;
    }

    private boolean isMastercard(int firstTwoDigit) {
        return firstTwoDigit >= 51 && firstTwoDigit <= 55;
    }
}
