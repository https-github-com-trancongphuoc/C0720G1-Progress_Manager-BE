package com.codegym.service;

import com.codegym.entity.InfoTopicRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InfoTopicRegisterService {
    Page<InfoTopicRegister> getListProcess(Pageable pageable);

    InfoTopicRegister getProcessDetailById(Integer id);

    InfoTopicRegister getProcessDetailByGroupId(Integer id);

    void registerInfoTopic(InfoTopicRegister infoTopicRegister);

    Page<InfoTopicRegister> getListTopicNotApproval(Integer idFaculty, Pageable pageable);
}
