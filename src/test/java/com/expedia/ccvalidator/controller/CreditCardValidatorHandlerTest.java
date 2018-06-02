package com.expedia.ccvalidator.controller;

import com.expedia.ccvalidator.validator.BasicValidator;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import ratpack.handling.Context;
import ratpack.http.Request;
import ratpack.http.Response;
import ratpack.util.MultiValueMap;
import ratpack.util.internal.ImmutableDelegatingMultiValueMap;

import static com.google.common.collect.ImmutableMap.of;
import static org.mockito.Mockito.*;
import static ratpack.http.Status.OK;
import static ratpack.util.internal.ImmutableDelegatingMultiValueMap.empty;

public class CreditCardValidatorHandlerTest {

    private CreditCardValidatorHandler handler;
    private Context context = mock(Context.class);
    private Request request = mock(Request.class);
    private Response response = mock(Response.class);

    @Before
    public void setUp() {
        handler = new CreditCardValidatorHandler(new BasicValidator());
        ImmutableDelegatingMultiValueMap params = aCreditCard("4155555555555555");
        when(request.getQueryParams()).thenReturn(params);
        when(context.getRequest()).thenReturn(request);
        when(context.getResponse()).thenReturn(response);
    }

    @Test
    public void returnsBadParameters() {
        MultiValueMap<String, String> params = empty();
        when(request.getQueryParams()).thenReturn(params);

        handler.handle(context);

        verify(context).render("Bad Parameters");
        verify(response).status(400);
    }

    @Test
    public void returnsOk() {
        handler.handle(context);

        verify(context).render("Ok credit card!");
        verify(response).status(OK);
    }

    @Test
    public void returnsErrorMessage() {
        ImmutableDelegatingMultiValueMap params = aCreditCard("bad-credit-card-number");
        when(request.getQueryParams()).thenReturn(params);
        handler.handle(context);

        verify(context).render("Does not contain 16 characters");
        verify(response).status(400);
    }

    private ImmutableDelegatingMultiValueMap aCreditCard(String cardNumber) {
        return new ImmutableDelegatingMultiValueMap(of("credit-card", ImmutableList.of(cardNumber),
                "expiration", ImmutableList.of("05-2099")));
    }
}