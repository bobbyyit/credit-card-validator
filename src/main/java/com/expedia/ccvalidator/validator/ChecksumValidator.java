package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.CreditCard;

import java.util.Optional;

import static java.lang.Integer.valueOf;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ChecksumValidator implements Validator {

    @Override
    public Optional<String> validate(CreditCard creditCard) {
        if (passesLuhnsAlgorithm(creditCard.getNumber())) {
            return empty();
        } else {
            return of("Not a valid credit card");
        }
    }

    private boolean passesLuhnsAlgorithm(String creditCartNumber) {
        String[] number = creditCartNumber.split("");
        boolean isOddIndexFromRight = true;
        int sumOfOddIndex = 0, sumOfEvenIndex = 0;
        for (int indexStartingFromLeft = number.length; indexStartingFromLeft > 0; indexStartingFromLeft --) {
            int digit = valueOf(number[indexStartingFromLeft - 1]);
            if (isOddIndexFromRight) {
                sumOfOddIndex += digit;
            } else {
                int digitTimesTwo = digit * 2;
                int sumOfDigits;
                if (has2Digits(digitTimesTwo)) {
                    sumOfDigits = (digitTimesTwo % 10) + 1;
                } else {
                    sumOfDigits = digitTimesTwo;
                }
                sumOfEvenIndex += sumOfDigits;
            }

            isOddIndexFromRight = !isOddIndexFromRight;
        }

        int sum = (sumOfEvenIndex + sumOfOddIndex);
        return sum % 10 == 0;
    }

    private boolean has2Digits(int digitTimesTwo) {
        return digitTimesTwo > 9;
    }
}
