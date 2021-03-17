package com.codegym.repository;

import com.codegym.entity.GroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Integer> {
    @Modifying
    @Query(
            value = "insert into group_account(`name`,delete_flag) value (?1,false)",
            nativeQuery = true)
    void saveGroup(String name);

    @Modifying
    @Query(
            value = "insert into account_role(account_id,role_id) value (?1,3)",
            nativeQuery = true)
    void saveGroup(Integer leader_id);

    @Modifying
    @Query(
            value = "update student " +
                    "set group_account_id = ?1 " +
                    "where account_id = ?2",
            nativeQuery = true)
    void acceptJoinGroup(Integer groupId, Integer accountId);
}

