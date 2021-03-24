package com.codegym.service.impl;

import com.codegym.entity.InfoTopicRegister;
import com.codegym.repository.InfoTopicRegisterRepository;
import com.codegym.service.InfoTopicRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoTopicRegisterServiceImpl implements InfoTopicRegisterService {

    @Autowired
    private InfoTopicRegisterRepository infoTopicRegisterRepository;

    @Override
    public Page<InfoTopicRegister> getListProcess(Pageable pageable) {
        return infoTopicRegisterRepository.findAll(pageable);
    }

    @Override
    public InfoTopicRegister getProcessDetailById(Integer id) {
        return infoTopicRegisterRepository.findById(id).orElse(null);
    }

    @Override
    public InfoTopicRegister getProcessDetailByGroupId(Integer id) {
        return infoTopicRegisterRepository.findByGroupAccount_IdAndStatusCompleteFalse(id);
    }

    @Override
    public void registerInfoTopic(InfoTopicRegister infoTopicRegister) {
        infoTopicRegisterRepository.save(infoTopicRegister);
    }

    @Override
    public Page<InfoTopicRegister> getListTopicNotApproval(Integer idFaculty, Pageable pageable) {
        return infoTopicRegisterRepository.findAllByTopic_Faculty_IdAndStatusFalse(idFaculty, pageable);
    }
}
