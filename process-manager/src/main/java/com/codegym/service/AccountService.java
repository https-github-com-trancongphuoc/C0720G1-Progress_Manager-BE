package com.codegym.service;

import com.codegym.dto.StudentDTO;
import com.codegym.entity.Account;

public interface AccountService {

    Account findByUsername(String username);

    Account getAccountById(Integer idAccount);

    Account getAccountByIdStudent(Integer id);
}
