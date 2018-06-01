package com.expedia.ccvalidator.controller;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class CreditCardValidatorController implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.header("content-type", "text/html");
        ctx.render("1");
    }
}
