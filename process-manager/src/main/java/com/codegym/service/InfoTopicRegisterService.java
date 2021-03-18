package com.codegym.service;

import com.codegym.entity.InfoTopicRegister;

import java.util.List;

public interface InfoTopicRegisterService {
    List<InfoTopicRegister> getListProcess();

    InfoTopicRegister getProcessDetailById(Integer id);

    InfoTopicRegister getProcessDetailByGroupId(Integer id);
}
