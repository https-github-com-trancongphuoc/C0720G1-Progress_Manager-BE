package com.codegym.service;

import com.codegym.entity.Topic;

import java.util.List;

public interface TopicService {
    Topic registerTopic(Topic topic);

    List<Topic> getListTopicByFacultyId(Integer id);
}
