package com.codegym.dto;

import com.codegym.entity.Account;

public class JwtResponse {

    private String token;

    private Account account;

    public JwtResponse() {
    }

    public JwtResponse(String token, Account account) {
        this.token = token;
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
