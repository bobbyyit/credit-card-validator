package com.expedia.ccvalidator.validator;

public class CreditCart {
    private String number;
    private String expiration;

    public CreditCart(String number, String expiration) {
        this.number = number;
        this.expiration = expiration;
    }

    public String getNumber() {
        return number;
    }

    public String getExpiration() {
        return expiration;
    }
}
