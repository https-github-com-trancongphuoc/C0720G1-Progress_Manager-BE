package com.codegym.service.impl;

import com.codegym.dto.StudentDTO;
import com.codegym.entity.Account;
import com.codegym.repository.AccountRepository;
import com.codegym.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account getAccountById(Integer idAccount) {
        return accountRepository.findById(idAccount).orElse(null);
    }

    @Override
    public Account getAccountByIdStudent(Integer id) {
        return accountRepository.findByStudent_Id(id);
    }
}
