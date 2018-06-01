package com.expedia.ccvalidator;

import java.io.IOException;

public class CreditCardValidator {
    private final Server server;

    public static void main(String[] args) throws IOException {
        CreditCardValidator creditCardValidator = launch();
    }

    private static CreditCardValidator launch() throws IOException {
        CreditCardValidator creditCardValidator = new CreditCardValidator();
        creditCardValidator.start();
        return creditCardValidator;
    }

    private void start() throws IOException {
        server.start();
    }

    public CreditCardValidator() {
        server = new Server("0.0.0.0", 7777);
    }

}
