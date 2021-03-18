package com.codegym.service;

import com.codegym.entity.TopicProcess;


public interface TopicProcessService {

    /** LuyenNT
     */
    TopicProcess findById(Integer id);

    TopicProcess getProcessById(Integer idProcess);

    TopicProcess updateProcess(TopicProcess topicProcess);
}
