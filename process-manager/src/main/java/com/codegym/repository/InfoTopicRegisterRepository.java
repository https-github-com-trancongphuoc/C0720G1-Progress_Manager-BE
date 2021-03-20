package com.codegym.repository;

import com.codegym.entity.InfoTopicRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoTopicRegisterRepository extends JpaRepository<InfoTopicRegister, Integer> {

    InfoTopicRegister findByGroupAccount_Id(Integer groupAccount_id);
}
