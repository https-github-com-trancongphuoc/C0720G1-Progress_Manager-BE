package com.codegym.repository;

import com.codegym.entity.InfoTopicRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface InfoTopicRegisterRepository extends JpaRepository<InfoTopicRegister, Integer> {

    InfoTopicRegister findByGroupAccount_Id(Integer groupAccount_id);

    @Transactional
    @Query(value = "SELECT * FROM process_manager.info_topic_register " +
            "join (select * from student where student.group_account_id = ?1) AS student_tmp " +
            "on student_tmp.group_account_id = info_topic_register.group_account_id", nativeQuery = true)
    InfoTopicRegister findAllByGroupAccount(Integer id);
}
