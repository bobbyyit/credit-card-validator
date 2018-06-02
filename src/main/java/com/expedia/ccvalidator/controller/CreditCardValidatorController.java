package com.expedia.ccvalidator.controller;

import com.expedia.ccvalidator.pojo.CreditCard;
import com.expedia.ccvalidator.validator.Validator;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.join;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static ratpack.http.Status.OK;

public class CreditCardValidatorController implements Handler {

    private Validator[] validators;

    public CreditCardValidatorController(Validator... validators) {
        this.validators = validators;
    }

    @Override
    public void handle(Context ctx) {
        ctx.header("content-type", "text/plain");
        Map<String, String> parameters = ctx.getRequest().getQueryParams();

        if (isInvalidFormat(parameters)) {
            ctx.getResponse().status(400);
            ctx.render("Bad Parameters");
            return;
        }

        CreditCard card = new CreditCard(parameters.get("credit-card"), parameters.get("expiration"));

        String errorMessage = validateCreditCard(card);
        if (hasErrors(errorMessage)) {
            ctx.getResponse().status(400);
            ctx.render(errorMessage);
        } else {
            ctx.getResponse().status(OK);
            ctx.render("Ok credit card!");
        }
    }

    private boolean hasErrors(String allMessages) {
        return !isEmpty(allMessages);
    }

    private String validateCreditCard(CreditCard card) {
        return join(", ", iterateValidators(card));
    }

    private List<String> iterateValidators(CreditCard card) {
        List<String> errorMessages = new ArrayList<>();
        for (Validator validator : validators) {
            Optional<String> validatorResult = validator.validate(card);
            validatorResult.ifPresent(errorMessages::add);
        }
        return errorMessages;
    }

    private boolean isInvalidFormat(Map<String, String> parameters) {
        return !parameters.containsKey("credit-card") && !parameters.containsKey("expiration");
    }
}
