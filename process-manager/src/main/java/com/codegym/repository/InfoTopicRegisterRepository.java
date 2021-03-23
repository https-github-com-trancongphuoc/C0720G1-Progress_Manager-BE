package com.codegym.repository;

import com.codegym.entity.InfoTopicRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoTopicRegisterRepository extends JpaRepository<InfoTopicRegister, Integer> {

    InfoTopicRegister findByGroupAccount_IdAndStatusCompleteFalse(Integer groupAccount_id);

    Page<InfoTopicRegister> findAllByTopic_Faculty_IdAndStatusFalse(Integer topic_faculty_id, Pageable pageable);
}
