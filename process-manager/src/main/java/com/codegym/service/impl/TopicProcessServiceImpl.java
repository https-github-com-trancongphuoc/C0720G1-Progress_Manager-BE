package com.codegym.service.impl;

import com.codegym.entity.TopicProcess;
import com.codegym.repository.TopicProcessRepository;
import com.codegym.service.TopicProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicProcessServiceImpl implements TopicProcessService {

    @Autowired
    private TopicProcessRepository topicProcessRepository;

    /** LuyenNT
     * @return
     */
    @Override
    public TopicProcess findById(Integer id) {
        return topicProcessRepository.getByIdTopicProcess(id);
    }

    @Override
    public Optional<TopicProcess> findById1(Integer id) {
        return topicProcessRepository.findById(id);
    }

    @Override
    public TopicProcess getProcessById(Integer idProcess) {
        return topicProcessRepository.findById(idProcess).orElse(null);
    }

    @Override
    public TopicProcess updateProcess(TopicProcess topicProcess) {
        return topicProcessRepository.save(topicProcess);
    }
}
