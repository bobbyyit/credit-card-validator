package com.expedia.ccvalidator;

import com.expedia.ccvalidator.controller.CreditCardValidatorController;
import ratpack.config.ConfigData;
import ratpack.health.HealthCheckHandler;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

import static java.net.InetAddress.getByName;
import static ratpack.server.BaseDir.find;
import static ratpack.server.RatpackServer.start;

public class test {
    public static void main(String[] args) throws Exception {
        start(spec -> spec
                .serverConfig(ServerConfig.embedded().port(8888)
                        .address(getByName("0.0.0.0"))
                        .baseDir(find()))
                .handlers(chain -> {
                            chain
                                    .get("", ctx -> ctx.render("You're up and running"))
                                    .get("validate", new CreditCardValidatorController());
                        }
                )
        );
    }
}
