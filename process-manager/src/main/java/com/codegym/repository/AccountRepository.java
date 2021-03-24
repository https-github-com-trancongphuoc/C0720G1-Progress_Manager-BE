package com.codegym.repository;

import com.codegym.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);

    Account findByStudent_Id(Integer student_id);

    Account findByTeacher_Id(Integer teacher_id);
}
