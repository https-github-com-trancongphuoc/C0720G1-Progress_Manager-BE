package com.codegym.service.impl;


import com.codegym.entity.Account;
import com.codegym.entity.AccountRole;
import com.codegym.repository.AccountRepository;
import com.codegym.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the databases" );
        }

        List<AccountRole> accountRoleList = accountRoleRepository.findAllByAccount(account);

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        if (accountRoleList != null) {
            for (AccountRole accountRole: accountRoleList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(accountRole.getRole().getName());
                grantedAuthorityList.add(grantedAuthority);
            }
        }

        return new User(account.getUsername(), account.getPassword(), grantedAuthorityList);
    }
}
