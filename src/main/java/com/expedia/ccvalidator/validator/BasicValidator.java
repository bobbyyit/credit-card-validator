package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.CreditCard;

import java.util.Calendar;
import java.util.Optional;

import static java.lang.Integer.valueOf;
import static java.util.Calendar.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class BasicValidator implements Validator {

    @Override
    public Optional<String> validate(CreditCard creditCard) {
        String creditCartNumber = creditCard.getNumber();
        Calendar now = getInstance();
        Calendar expirationDate = setExpirationDate(creditCard);

        if(now.after(expirationDate)) {
            return of("Credit card has expired");
        }
        if (!has16Characters(creditCard.getNumber())) {
            return of("Does not contain 16 characters");
        }
        if (!isNumeric(creditCartNumber)) {
            return of("Contains illegal characters");
        }
        if (!(isVisa(creditCartNumber) || isMastercard(creditCartNumber))) {
            return of("Not Visa/Mastercard");
        }

        return empty();
    }

    private Calendar setExpirationDate(CreditCard creditCard) {
        Calendar expirationDate = getInstance();
        String[] expiration = creditCard.getExpiration().split("-");
        expirationDate.set(YEAR, valueOf(expiration[1]));
        expirationDate.set(MONTH, valueOf(expiration[0]));
        return expirationDate;
    }

    private boolean has16Characters(String creditCartNumber) {
        return creditCartNumber.length() == 16;
    }

    private boolean isVisa(String creditCartNumber) {
        int firstTwoDigit = getFirstTwoDigit(creditCartNumber);
        return firstTwoDigit >= 40 && firstTwoDigit <= 49;
    }

    private boolean isMastercard(String creditCartNumber) {
        int firstTwoDigit = getFirstTwoDigit(creditCartNumber);
        return firstTwoDigit >= 51 && firstTwoDigit <= 55;
    }

    private Integer getFirstTwoDigit(String creditCartNumber) {
        return valueOf(creditCartNumber.substring(0, 2));
    }
}
