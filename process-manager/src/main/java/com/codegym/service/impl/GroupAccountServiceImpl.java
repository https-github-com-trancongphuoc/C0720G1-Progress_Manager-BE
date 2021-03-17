package com.codegym.service.impl;

import com.codegym.repository.GroupAccountRepository;
import com.codegym.repository.StudentRepository;
import com.codegym.service.GroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupAccountServiceImpl implements GroupAccountService {

    @Autowired
    private GroupAccountRepository groupAccountRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveGroup(String name, Integer leaderId) {
        groupAccountRepository.saveGroup(name);
        groupAccountRepository.saveGroup(leaderId);
    }

    @Override
    public void acceptJoinGroup(Integer groupId, Integer accountId) {
        groupAccountRepository.acceptJoinGroup(groupId,accountId);
    }
}
