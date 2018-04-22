package com.expedia.ccvalidator;


import com.expedia.ccvalidator.server.Server;

import java.io.IOException;

public class CreditCardValidator {
    private final Server server;

    public CreditCardValidator() {
        server = new Server("0.0.0.0", 7777);
    }
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
}
