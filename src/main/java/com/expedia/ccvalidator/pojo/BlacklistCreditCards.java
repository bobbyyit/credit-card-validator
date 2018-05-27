package com.expedia.ccvalidator.pojo;

import java.util.List;

public class BlacklistCreditCards {
    private List<String> blacklist;

    public List<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(List<String> blacklist) {
        this.blacklist = blacklist;
    }
}
