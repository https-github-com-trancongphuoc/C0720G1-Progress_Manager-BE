package com.codegym.repository;

import com.codegym.entity.Account;
import com.codegym.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {
    List<AccountRole> findAllByAccount(Account account);
}
