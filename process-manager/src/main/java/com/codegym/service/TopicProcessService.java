package com.codegym.service;

import com.codegym.entity.TopicProcess;

import java.util.Optional;


public interface TopicProcessService {

    /** LuyenNT
     * @return
     */

    TopicProcess getProcessById(Integer idProcess);

    TopicProcess updateProcess(TopicProcess topicProcess);

    Optional<TopicProcess> findById(Integer id);

}
