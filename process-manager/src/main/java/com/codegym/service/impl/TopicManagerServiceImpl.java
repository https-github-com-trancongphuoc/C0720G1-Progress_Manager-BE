package com.codegym.service.impl;

import com.codegym.entity.InfoTopicRegister;
import com.codegym.entity.Student;
import com.codegym.entity.Topic;
import com.codegym.repository.InfoTopicRegisterRepository;
import com.codegym.repository.StudentTopicRepository;
import com.codegym.repository.TopicManagerRepository;
import com.codegym.service.TopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicManagerServiceImpl implements TopicManagerService {
    @Autowired
    TopicManagerRepository topicManagerRepository;
    @Autowired
    StudentTopicRepository studentTopicRepository;
    @Autowired
    InfoTopicRegisterRepository infoTopicRegisterRepository;


    @Override
    public Page<Topic> findAllByTopic(Pageable pageable) {
        return topicManagerRepository.findAllByTopic(pageable);
    }

    @Override
    public Page<Topic> findAllByTopicFind(String name, Pageable pageable) {
        return topicManagerRepository.findAllByTopicFind(name, pageable);
    }

    @Override
    public Topic findByIdTopic(Integer id) {
        return topicManagerRepository.findByIdTopic(id);
    }

    @Override
    public InfoTopicRegister findAllByGroupAccount(Integer id) {
        return infoTopicRegisterRepository.findAllByGroupAccount(id);
    }

    @Override
    public List<Student> findAllStudent(Integer id) {
        return studentTopicRepository.findAllStudent(id);
    }
}
