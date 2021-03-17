package com.codegym.repository;

import com.codegym.entity.TopicProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicProcessRepository extends JpaRepository<TopicProcess, Integer> {

    @Query(value = "select * from topic_process",nativeQuery = true)
    TopicProcess getById(Integer id);
}
