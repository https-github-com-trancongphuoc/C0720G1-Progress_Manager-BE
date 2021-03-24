package com.codegym.repository;

import com.codegym.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TopicManagerRepository extends JpaRepository<Topic, Integer> {
    @Query(value = "SELECT * FROM process_manager.topic ", nativeQuery = true)
    Page<Topic> findAllByTopic(Pageable pageable);

    @Query(value = "SELECT * FROM process_manager.topic where topic.name like %?1%", nativeQuery = true)
    Page<Topic> findAllByTopicFind(String name, Pageable pageable);

    @Query(value = "SELECT * FROM process_manager.topic where topic.id = ?1", nativeQuery = true)
    Topic findByIdTopic(Integer id);
}

