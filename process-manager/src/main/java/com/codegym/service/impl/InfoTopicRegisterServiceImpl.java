package com.codegym.service.impl;

import com.codegym.entity.InfoTopicRegister;
import com.codegym.repository.InfoTopicRegisterRepository;
import com.codegym.service.InfoTopicRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoTopicRegisterServiceImpl implements InfoTopicRegisterService {

    @Autowired
    private InfoTopicRegisterRepository infoTopicRegisterRepository;

    @Override
    public List<InfoTopicRegister> getListProcess() {
        return infoTopicRegisterRepository.findAll();
    }

    @Override
    public InfoTopicRegister getProcessDetailById(Integer id) {
        return infoTopicRegisterRepository.findById(id).orElse(null);
    }

    @Override
    public InfoTopicRegister getProcessDetailByGroupId(Integer id) {
        return infoTopicRegisterRepository.findByGroupAccount_Id(id);
    }
}
