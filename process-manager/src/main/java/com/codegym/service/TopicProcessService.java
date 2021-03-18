package com.codegym.service;

import com.codegym.entity.TopicProcess;

import java.util.Optional;


public interface TopicProcessService {

    /** LuyenNT
     * @return
     */
    Optional<TopicProcess> findById(Integer id);
}
