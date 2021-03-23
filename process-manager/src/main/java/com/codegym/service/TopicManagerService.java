package com.codegym.service;

import com.codegym.entity.InfoTopicRegister;
import com.codegym.entity.Student;
import com.codegym.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicManagerService {

    Page<Topic> findAllByTopic(Pageable pageable);

    Page<Topic> findAllByTopicFind(String name, Pageable pageable);

    Topic findByIdTopic(Integer id);

    InfoTopicRegister findAllByGroupAccount(Integer id);

    List<Student> findAllStudent(Integer id);
}
