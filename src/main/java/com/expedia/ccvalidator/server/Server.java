package com.expedia.ccvalidator.server;

import com.vtence.molecule.WebServer;
import com.vtence.molecule.servers.SimpleServer;

import java.io.IOException;

public class Server {

    private final WebServer server;

    public Server(String host, int port) {
        server = new WebServer(new SimpleServer(host, port, 100));
    }

    public void start() throws IOException {
    }
}
