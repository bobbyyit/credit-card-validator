package com.expedia.ccvalidator;

import com.expedia.ccvalidator.controller.CreditCardValidatorHandler;
import com.expedia.ccvalidator.validator.BasicValidator;
import com.expedia.ccvalidator.validator.BlackListedCardValidator;
import com.expedia.ccvalidator.validator.BlacklistLoader;
import com.expedia.ccvalidator.validator.ChecksumValidator;
import ratpack.server.ServerConfig;

import static java.net.InetAddress.getByName;
import static ratpack.server.BaseDir.find;
import static ratpack.server.RatpackServer.start;

public class CreditCardValidator {
    public static void main(String[] args) throws Exception {
        start(spec -> spec
                .serverConfig(ServerConfig.embedded().port(8888)
                        .address(getByName("0.0.0.0"))
                        .baseDir(find()))
                .handlers(chain -> {
                    BlacklistLoader blacklistLoader = new BlacklistLoader("/black-listed-credit-cards.json");

                    BasicValidator basicValidator = new BasicValidator();
                    BlackListedCardValidator blackListedCardValidator = new BlackListedCardValidator(blacklistLoader.load());
                    ChecksumValidator checksumValidator = new ChecksumValidator();
                    chain
                                    .get("", ctx -> ctx.render("You're up and running"))
                                    .get("validate", new CreditCardValidatorHandler(basicValidator, blackListedCardValidator, checksumValidator));
                        }
                )
        );
    }
}
