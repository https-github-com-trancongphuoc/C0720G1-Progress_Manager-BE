package com.codegym.repository;

import com.codegym.entity.TopicProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface TopicProcessRepository extends JpaRepository<TopicProcess, Integer> {


    @Query(value = "SELECT * FROM topic_process where id = ?",nativeQuery = true)
    TopicProcess getByIdTopicProcess(Integer id);
}
