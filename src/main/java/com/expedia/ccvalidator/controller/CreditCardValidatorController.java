package com.expedia.ccvalidator.controller;

import com.expedia.ccvalidator.validator.BasicValidator;
import com.expedia.ccvalidator.validator.BlackListedCardValidator;
import com.expedia.ccvalidator.validator.ChecksumValidator;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class CreditCardValidatorController implements Handler {

    private final BasicValidator basicValidator;
    private final BlackListedCardValidator blackListedCardValidator;
    private final ChecksumValidator checksumValidator;

    public CreditCardValidatorController(BasicValidator basicValidator, BlackListedCardValidator blackListedCardValidator, ChecksumValidator checksumValidator) {
        this.basicValidator = basicValidator;
        this.blackListedCardValidator = blackListedCardValidator;
        this.checksumValidator = checksumValidator;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.header("content-type", "text/html");
        ctx.render("1");
    }
}
