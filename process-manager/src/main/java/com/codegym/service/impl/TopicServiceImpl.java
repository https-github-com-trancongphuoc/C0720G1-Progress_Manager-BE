package com.codegym.service.impl;

import com.codegym.entity.Topic;
import com.codegym.repository.TopicRepository;
import com.codegym.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic registerTopic(Topic topic) {
        return topicRepository.save(topic);
    }
}
