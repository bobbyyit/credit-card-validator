package com.expedia.ccvalidator.controller;

import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static com.vtence.molecule.http.HttpStatus.BAD_REQUEST;
import static com.vtence.molecule.http.HttpStatus.OK;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditCardNumberControllerTest {
    private Response response = new Response();
    private Request request = new Request();
    private CreditCardNumberController controller;

    @Before
    public void init() {
        controller = new CreditCardNumberController();
    }

    @Test
    public void returnsOk() throws Exception {
        controller.handle(request, response);

        assertThat(response.statusCode(), is(OK.code));
    }

    @Test
    public void returnContentType() throws Exception {
        controller.handle(request, response);

        assertThat(response.contentType(), is("text/html; charset=UTF-8"));
    }

    @Test
    public void returnsBadParameter() throws Exception {
        request.addParameter("number", "1234567567765");
        request.addParameter("expirationDate", "0");
        controller.handle(request, response);

        assertThat(response.statusCode(), is(BAD_REQUEST.code));
    }
}