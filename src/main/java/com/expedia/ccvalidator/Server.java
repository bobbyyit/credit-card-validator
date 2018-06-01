package com.expedia.ccvalidator;

import com.expedia.ccvalidator.controller.CreditCardNumberController;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.routing.DynamicRoutes;
import com.vtence.molecule.servers.SimpleServer;

import java.io.IOException;


public class Server {
    private final WebServer server;

    public Server(String host, int port) {
        this.server = new WebServer(new SimpleServer(host, port, 100));
    }

    public void start() throws IOException {
        server.start(new DynamicRoutes() {{
            get("/login").to(new CreditCardNumberController());
        }});
    }
}
