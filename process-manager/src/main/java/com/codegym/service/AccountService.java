package com.codegym.service;

import com.codegym.entity.Account;

public interface AccountService {

    Account findByUsername(String username);

    Account getAccountById(Integer idAccount);

    Account getAccountByIdStudent(Integer id);

    Account getAccountByIdTeacher(Integer id);
  
    void changePassword(Account account);

    Account registerAccount(Account account);
}
