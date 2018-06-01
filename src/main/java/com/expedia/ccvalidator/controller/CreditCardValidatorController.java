package com.expedia.ccvalidator.controller;

import com.expedia.ccvalidator.pojo.CreditCard;
import com.expedia.ccvalidator.validator.BasicValidator;
import com.expedia.ccvalidator.validator.BlackListedCardValidator;
import com.expedia.ccvalidator.validator.ChecksumValidator;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;

import java.util.Map;
import java.util.Optional;

import static ratpack.http.Status.OK;

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
        ctx.header("content-type", "text/plain");
        Map<String, String> parameters = ctx.getRequest().getQueryParams();
        ctx.getResponse().status(OK);
        ctx.render("Ok!");

        if (!parameters.containsKey("credit-card") && !parameters.containsKey("expiration")) {
            ctx.getResponse().status(400);
            ctx.render("Bad Parameters");
            return;
        }

        CreditCard card = new CreditCard(parameters.get("credit-card"), parameters.get("expiration"));

        basicValidator.validate(card).ifPresent(ctx::render);
        blackListedCardValidator.validate(card).ifPresent(ctx::render);
        checksumValidator.validate(card).ifPresent(ctx::render);
    }
}
