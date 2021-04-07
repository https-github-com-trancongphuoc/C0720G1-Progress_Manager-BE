package com.codegym.service.impl;

import com.codegym.dto.AccountRoleDTO;
import com.codegym.repository.AccountRoleRepository;
import com.codegym.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Override
    public void registerAccountRole(AccountRoleDTO accountRoleDTO) {
        accountRoleRepository.createRole(accountRoleDTO.getAccountId(), accountRoleDTO.getRoleId());
    }
}
