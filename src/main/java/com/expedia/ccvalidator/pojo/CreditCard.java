package com.expedia.ccvalidator.pojo;

public class CreditCard {
    private String number;
    private String expiration;

    public CreditCard(String number, String expiration) {
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
