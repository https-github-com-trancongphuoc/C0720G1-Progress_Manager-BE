package com.codegym.service;

import com.codegym.entity.Account;

public interface AccountService {

    Account findByUsername(String username);
}
