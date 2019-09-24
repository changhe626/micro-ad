package com.onyx.ad.sponsor.vo;

import org.apache.commons.lang.StringUtils;

public class CreateUserRequest {

    private String username;

    public boolean validate() {
        return !StringUtils.isEmpty(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CreateUserRequest(String username) {
        this.username = username;
    }

    public CreateUserRequest() {
    }
}