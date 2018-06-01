package com.expedia.ccvalidator.controller;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;

public class CreditCardValidatorController implements Application {
    @Override
    public void handle(Request request, Response response) throws Exception {
        response.status(HttpStatus.OK);
    }
}
