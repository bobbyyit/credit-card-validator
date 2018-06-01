package com.expedia.ccvalidator;

import com.expedia.ccvalidator.controller.CreditCardValidatorController;
import ratpack.server.RatpackServer;

import java.net.InetAddress;

import static java.net.InetAddress.getByName;
import static ratpack.server.BaseDir.find;
import static ratpack.server.RatpackServer.of;

public class CreditCardValidator {
    private RatpackServer server;
    private String host;
    private int port;

    public CreditCardValidator(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        launch("0.0.0.0", 7777);
    }

    private static CreditCardValidator launch(String host, int port) throws Exception {
        CreditCardValidator creditCardValidator = new CreditCardValidator(host, port);
        creditCardValidator.start();
        return creditCardValidator;
    }

    private void start() throws Exception {
        server = of(server ->
                server.serverConfig(serverConfig ->
                        serverConfig
                                .port(port)
                                .address(getByName(host))
                                .baseDir(find()))
                        .handlers(
                                chain -> {
                                    chain.get("validate", new CreditCardValidatorController());
                                }
                        )
        );
        server.start();
    }
}
