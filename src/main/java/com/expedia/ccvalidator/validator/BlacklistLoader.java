package com.expedia.ccvalidator.validator;

import com.expedia.ccvalidator.pojo.BlacklistCreditCards;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BlacklistLoader {
    private String path;

    public BlacklistLoader(String path) {
        this.path = path;
    }

    public BlacklistCreditCards load() throws IOException {
        URL url = getClass().getResource(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(url.getPath()), BlacklistCreditCards.class);
    }
}
